package com.ssafy.live.model.service;

import java.util.List;

import com.ssafy.live.model.dto.Member;

public interface SimpleService {
    public List<String> getGugu(int dan);
    public int join(Member member) throws Exception; // 회원가입 메서드 추가
}
