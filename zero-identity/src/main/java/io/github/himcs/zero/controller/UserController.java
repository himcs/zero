package io.github.himcs.zero.controller;

import io.github.himcs.zero.domain.User;
import io.github.himcs.zero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class UserController {
    private UserService service;

    @Autowired
    public UserController(final UserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<String> register(@RequestBody final UserRegistrationRequest registration)
            throws URISyntaxException {
        User user = this.service.register(registration.getUsername(), registration.getPassword());
        return ResponseEntity
                .created(new URI("/users/" + user.getId()))
                .build();
    }

    @GetMapping("/users")
    public List<UserInfoResponse> list() {
        return this.service.list().stream()
                .map(user -> new UserInfoResponse(user.getName(), user.getPassword()))
                .collect(toList());
    }
}
