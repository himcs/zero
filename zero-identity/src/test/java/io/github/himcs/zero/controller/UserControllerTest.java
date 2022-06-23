package io.github.himcs.zero.controller;

import com.google.common.collect.ImmutableList;
import io.github.himcs.zero.domain.User;
import io.github.himcs.zero.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

import static com.google.common.net.HttpHeaders.LOCATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {
    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void register() throws URISyntaxException {
        User mockUser = mock(User.class);
        when(this.userService.register("name", "password"))
                .thenReturn(mockUser);
        when(mockUser.getId()).thenReturn(1L);

        ResponseEntity<String> entity = this.userController.register(new UserRegistrationRequest("name", "password"));
        assertThat(entity.getStatusCodeValue()).isEqualTo(201);
        assertThat(entity.getHeaders().get(LOCATION).get(0)).isEqualTo("/users/1");
    }

    @Test
    public void should_list_all_users() {
        when(this.userService.list()).thenReturn(ImmutableList.of(new User("name", "password")));
        List<UserInfoResponse> users = this.userController.list();
        assertThat(users.size()).isEqualTo(1);
        UserInfoResponse info = users.get(0);
        assertThat(info.getUsername()).isEqualTo("name");
        assertThat(info.getPassword()).isEqualTo("password");
    }
}