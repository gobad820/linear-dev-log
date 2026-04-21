package com.ssafy.live.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ssafy.live.model.dto.Member;

public class FileMemberDao implements MemberDao {
    // TODO: 14.FileMemberDao를 분석해 보세요. 
    private static final FileMemberDao instance = new FileMemberDao();
    private final File dataFile;
    private List<Member> members;

    public static FileMemberDao getInstance() {
        return instance;
    }

    private FileMemberDao() {
        // file 초기화
        String path = FileMemberDao.class.getResource("/").getPath() + "member.dat";
        dataFile = new File(path);

        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(Member member) {
        synchronized (members) {
            if (members.stream().anyMatch(m -> m.getEmail().equals(member.getEmail()))) {
                throw new RuntimeException("이미 가입된 회원입니다: " + member.getEmail());
            }
            member.setMno(System.currentTimeMillis());  // mno는 자동 설정
            members.add(member);
            System.out.println("회원 가입 성공: "+member);
            return 1;
        }
    }

    // TODO: 15: 무거운 작업인 load와 save는 언제 호출하는게 좋을지 생각해보세요. 
    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            members = (List) ois.readObject();
            System.out.println("회원 정보 로딩 완료: " + members.size() + "명");
        } catch (Exception e) {
            System.out.println("저장된 회원 정보가 없습니다.");
            members = Collections.synchronizedList(new ArrayList<>());
        }
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(members);
            System.out.println("회원 정보 저장 완료");
        } catch (Exception e) {
            throw new RuntimeException("회원 정보 저장 실패", e);
        }
    }
}
