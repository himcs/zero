package io.github.himcs.zero.controller;

import lombok.Getter;

@Getter
public class UserInfoResponse {
    private final String username;
    private final String password;

    public UserInfoResponse(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
}
