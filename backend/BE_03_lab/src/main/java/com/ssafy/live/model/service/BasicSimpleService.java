package com.ssafy.live.model.service;

import java.util.List;

import com.ssafy.live.model.dao.FileMemberDao;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;

public class BasicSimpleService implements SimpleService {

    // TODO: 03. Singleton Design Pattern 적용 및 getGugu 메서드를 분석해보세요. 
    private static BasicSimpleService service = new BasicSimpleService();

    public static BasicSimpleService getService() {
        return service;
    }
    private BasicSimpleService() {
    }

    @Override
    public List<String> getGugu(int dan) {
        if (dan < 1 || dan > 9) {
            // 범위를 넘는 요청에 대해서는 runtime exception 처리
            throw new RuntimeException("구구단 " + dan+"단");
        }
        List<String> list = new java.util.ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(dan + " * " + i + " = " + (dan * i));
        }
        return list;
    }


    private final MemberDao memberDao = FileMemberDao.getInstance();

    @Override
    public int join(Member member) {
        // 단순한 셔틀 역할 보다는 transaction 처리, 예외 처리 등 역할이 있을 수 있다.
        return memberDao.insert(member);
    }
}
