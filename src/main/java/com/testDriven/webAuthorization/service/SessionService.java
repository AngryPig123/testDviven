package com.testDriven.webAuthorization.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * packageName    : com.testDriven.webAuthorization.service
 * fileName       : SessionService
 * author         : AngryPig123
 * date           : 26. 1. 22.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 22.        AngryPig123       최초 생성
 */
public class SessionService {

    public void find() {
        HttpSession session = this.getHeepServletRequest().getSession();
        String authSession = (String) session.getAttribute("AUTH");
    }

    private HttpServletRequest getHeepServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(requestAttributes)) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        } else {
            throw new RuntimeException("RequestContextHolder.getRequestAttributes() is null");
        }
    }

}
