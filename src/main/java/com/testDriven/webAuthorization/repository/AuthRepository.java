package com.testDriven.webAuthorization.repository;

import com.testDriven.webAuthorization.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.testDriven.webAuthorization.repository
 * fileName       : AuthRepository
 * author         : AngryPig123
 * date           : 26. 1. 22.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 22.        AngryPig123       최초 생성
 */
public class AuthRepository {

    private static final Map<String, Integer> AUTH_REPOSITORY = new HashMap<>();

    public static Map<String, Integer> getInstance() {
        return AUTH_REPOSITORY;
    }

}
