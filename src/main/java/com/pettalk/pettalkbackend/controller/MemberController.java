package com.pettalk.pettalkbackend.controller;

import com.pettalk.pettalkbackend.dto.member.LoginRequest;
import com.pettalk.pettalkbackend.dto.member.LoginResponse;
import com.pettalk.pettalkbackend.entity.Member;
import com.pettalk.pettalkbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/login")
    public Member login (@RequestBody LoginRequest loginRequest) {

        String loginId = loginRequest.getLoginId();
        String password = loginRequest.getPassword();

        log.info(password);
        // loginId 로 member 테이블에 있는 회원정보를 찾는다.
        Member member = memberRepository.findByMemberId(loginId);

        return member;
    }
}
