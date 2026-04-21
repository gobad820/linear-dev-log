package com.ssafy.live.model.service;

import com.ssafy.live.model.dto.Member;

public interface MemberService {

    int registMember(Member member) ;

    Member login(String email, String password) ;
}
