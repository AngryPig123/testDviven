package com.testDriven.webAuthorization.entity;

/**
 * packageName    : com.testDriven.webAuthorization.entity
 * fileName       : User
 * author         : AngryPig123
 * date           : 26. 1. 22.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 22.        AngryPig123       최초 생성
 */
public class User {

    private String username;
    private String password;
    private boolean mfa;

    public User(String username, String password, boolean mfa) {
        this.username = username;
        this.password = password;
        this.mfa = mfa;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isMfa() {
        return mfa;
    }
}
