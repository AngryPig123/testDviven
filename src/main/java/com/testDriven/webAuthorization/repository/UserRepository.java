package com.testDriven.webAuthorization.repository;

import com.testDriven.webAuthorization.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.testDriven.webAuthorization.repository
 * fileName       : WebAuthorizationRepository
 * author         : AngryPig123
 * date           : 26. 1. 22.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 22.        AngryPig123       최초 생성
 */
public class UserRepository {

    private static final List<User> USER_REPOSITORY = new ArrayList<>();

    static {
        USER_REPOSITORY.add(new User("홍길동", "Qawsedrf!234", true));
        USER_REPOSITORY.add(new User("임꺽정", "Qawsedrf!234", false));
    }

    public static List<User> getInstance() {
        return USER_REPOSITORY;
    }

}
