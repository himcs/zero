package io.github.himcs.zero.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class UserRegistrationRequest {
    @Getter
    private final String username;
    @Getter
    private final String password;

    @JsonCreator
    public UserRegistrationRequest(@JsonProperty("username") final String username,
                                   @JsonProperty("password") final String password) {
        this.username = username;
        this.password = password;
    }

}
