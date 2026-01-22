package com.testDriven.webAuthorization;

import com.testDriven.webAuthorization.service.LoginService;
import com.testDriven.webAuthorization.session.PreAuth;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Objects;

/**
 * packageName    : com.testDriven.webAuthorization
 * fileName       : WebAuthorizationTest
 * author         : AngryPig123
 * date           : 26. 1. 22.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 22.        AngryPig123       최초 생성
 */
public class WebAuthorizationTest {

    private MockHttpServletRequest request;

    @BeforeEach
    void tearUp() {
        request = new MockHttpServletRequest();
    }

    @AfterEach
    void clear() {
        request = null;
    }

    //    1) 로그인 1단계(아이디/비밀번호)
    //    요청
    //    POST /login
    //    Body: { "username": "...", "password": "..." }
    //    성공 조건
    //    자격이 올바르면 서버는 임시 세션(PreAuth) 을 발급한다.
    //    응답: 200 OK
    //    응답 바디:
    //    { "next": "2FA_REQUIRED" } (mfaEnabled=true인 사용자)
    //    { "next": "AUTHENTICATED" } (mfaEnabled=false인 사용자)
    //    실패 조건
    //    자격이 틀리면 401 Unauthorized
    @Test
    void testFirstLoginStep() {
        request.setMethod("POST");
        request.setParameter("username", "홍길동");
        request.setParameter("password", "Qawsedrf!234");
        LoginService loginService = new LoginService();
        loginService.login(request);
    }

    @Test
    void testUserIsNull() {
        request.setMethod("POST");
        request.setParameter("username", "X");
        request.setParameter("password", "X");
        LoginService loginService = new LoginService();
        Assertions.assertThrows(RuntimeException.class, () -> {
            loginService.login(request);
        });
    }

    //    2) 2단계 인증 대상 판별
    //    사용자 속성 mfaEnabled=true인 경우만 2FA가 필요하다.
    //    mfaEnabled=false면 로그인 1단계 성공 시 즉시 정상 세션(Auth) 으로 전환된다.
    @Test
    void testMfaPreAuthType() {
        request.setMethod("POST");
        request.setParameter("username", "홍길동");
        request.setParameter("password", "Qawsedrf!234");
        LoginService loginService = new LoginService();
        loginService.login(request);
        Object session = Objects.requireNonNull(request.getSession()).getAttribute("PRE_AUTH");
        Assertions.assertInstanceOf(PreAuth.class, session);
    }

    //    3) 2FA 검증
    //            요청
    //            POST /mfa/verify
    //            Body: { "code": "123456" } (6자리)
    //            검증 규칙
    //            code는 발급 시각 기준 2분 내에만 유효
    //            같은 PreAuth 세션에서 최대 5회까지 시도 가능


    //        5회 초과 시 아래 중 하나로 통일해서 반환한다:
    //            423 Locked
    //            또는 429 Too Many Requests
    //            성공 응답
    //            성공 시 정상 세션(Auth) 으로 전환

    //        200 OK + { "next": "AUTHENTICATED" }
    //            실패 응답
    //            실패 시 400 Bad Request + 아래 중 하나:
    //            { "error": "INVALID_CODE" }
    //            { "error": "EXPIRED_CODE" }
    //            { "error": "LOCKED" }

}
