package io.github.himcs.zero.service;

import com.google.common.collect.ImmutableList;
import io.github.himcs.zero.domain.User;
import io.github.himcs.zero.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void should_register() {
        User register = userService.register("zhangsan", "Password");
        assertThat(register.getName()).isEqualTo("zhangsan");
        assertThat(register.getPassword()).isEqualTo("Password");
    }


    @Test
    void should_registered_register() {
        when(userRepository.findByName(any())).thenReturn(Optional.of(new User("zhangshan", "qwe")));
        assertThrows(IllegalArgumentException.class, () -> {
            userService.register("name", "password");
        });
    }

    @Test
    void should_list() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User("zhangshan", "qwe")));

        ImmutableList<User> list = userService.list();
        assertThat(list.get(0).getName()).isEqualTo("zhangshan");
        assertThat(list.get(0).getPassword()).isEqualTo("qwe");
    }
}