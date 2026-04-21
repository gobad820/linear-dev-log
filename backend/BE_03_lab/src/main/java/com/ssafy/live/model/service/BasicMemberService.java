package com.ssafy.live.model.service;

import com.ssafy.live.model.dao.FileMemberDao;
import com.ssafy.live.model.dao.MemberDao;
import com.ssafy.live.model.dto.Member;

public class BasicMemberService implements MemberService {
    private MemberDao dao = FileMemberDao.getInstance();

    private static BasicMemberService service = new BasicMemberService();

    private BasicMemberService() {
    }

    public static BasicMemberService getService() {
        return service;
    }

    @Override
    public int registMember(Member member)  {
        return dao.insert(member);
    }

    @Override
    public Member login(String email, String password) {
        return null;
    }

}
