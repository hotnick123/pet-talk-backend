package com.pettalk.pettalkbackend.service;

import com.pettalk.pettalkbackend.constants.HttpStatus;
import com.pettalk.pettalkbackend.constants.PetTalkError;
import com.pettalk.pettalkbackend.constants.ResultCode;
import com.pettalk.pettalkbackend.dto.user.LoginRequest;
import com.pettalk.pettalkbackend.dto.user.LoginResponse;
import com.pettalk.pettalkbackend.dto.user.SignUpRequest;
import com.pettalk.pettalkbackend.entity.User;
import com.pettalk.pettalkbackend.exception.PetTalkException;
import com.pettalk.pettalkbackend.jwt.TokenProvider;
import com.pettalk.pettalkbackend.repository.UserRepository;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * 로그인
     * @param loginRequest
     * @return
     */
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUserId(loginRequest.getLoginId());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            throw new PetTalkException("사용자를 찾을 수 없습니다.", ResultCode.CANNOT_FIND, null);
        }
        TokenProvider tokenProvider = new TokenProvider();
        String accessToken = tokenProvider.createToken(user.getUserId(), "PETTALK", "ACCESS_TOKEN");
        return new LoginResponse(accessToken);
    }

    /**
     * 회원 가입
     * @param signUpRequest
     * @return
     */
    public User signUp(SignUpRequest signUpRequest) {

        // ID 중복 체크
        if (userRepository.existsByUserId(signUpRequest.getUserId())) {
            throw new PetTalkException("중복된 아이디입니다.", ResultCode.INTERNAL_SERVER_ERROR, null);
        }

        // 유저 생성
        User user = User.builder()
                .userId(signUpRequest.getUserId())
                .password(signUpRequest.getPassword())
                .email(signUpRequest.getEmail())
                .nickname(signUpRequest.getNickname())
                .build();

        return userRepository.save(user);
    }
}
