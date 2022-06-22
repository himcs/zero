package io.github.himcs.zero.service;

import com.google.common.collect.ImmutableList;
import io.github.himcs.zero.domain.User;
import io.github.himcs.zero.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(final UserRepository repository) {
        this.repository = repository;
    }

    public User register(final String name, final String password) {
        if (this.repository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("User [" + name + "] is already registered");
        }

        User user = new User(name, password);
        this.repository.save(user);
        return user;
    }

    public ImmutableList<User> list() {
        return ImmutableList.copyOf(this.repository.findAll());
    }
}
