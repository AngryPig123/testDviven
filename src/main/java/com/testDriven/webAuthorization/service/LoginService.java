package com.testDriven.webAuthorization.service;


import com.testDriven.webAuthorization.entity.User;
import com.testDriven.webAuthorization.repository.UserRepository;
import com.testDriven.webAuthorization.session.Auth;
import com.testDriven.webAuthorization.session.PreAuth;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * packageName    : com.testDriven.webAuthorization
 * fileName       : LoginService
 * author         : AngryPig123
 * date           : 26. 1. 22.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 22.        AngryPig123       최초 생성
 */
public class LoginService {

    private final List<User> USER_LIST = UserRepository.getInstance();

    private final RuntimeException METHOD_NOT_ALLOWED = new RuntimeException(HttpStatus.METHOD_NOT_ALLOWED.value() + " " + "METHOD_NOT_ALLOWED");
    private final RuntimeException UNAUTHORIZED = new RuntimeException(HttpStatus.UNAUTHORIZED.value() + " UNAUTHORIZED");

    public void login(HttpServletRequest httpServletRequest) {

        String method = httpServletRequest.getMethod();
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        if (!"POST".equalsIgnoreCase(method)) {
            throw METHOD_NOT_ALLOWED;
        }

        if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
            throw UNAUTHORIZED;
        }

        User findUser = this.findUserByUsername(username);

        if (password.equals(findUser.getPassword())) {
            Auth auth;
            HttpSession session = httpServletRequest.getSession();
            if (findUser.isMfa()) {
                auth = new PreAuth();
                session.setAttribute("PRE_AUTH", auth);
            } else {
                auth = new Auth();
                session.setAttribute("AUTH", auth);
            }
        } else {
            throw UNAUTHORIZED;
        }

    }

    private User findUserByUsername(String username) {
        return USER_LIST.stream()
                .filter(item -> Objects.equals(username, item.getUsername()))
                .findFirst()
                .orElseThrow(() -> UNAUTHORIZED);
    }

}
