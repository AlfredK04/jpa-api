package com.booleanuk.api.service;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> getById(Integer id) {
        return this.userRepository.findById(id);
    }

    public User create(User user) {
        return this.userRepository.save(user);
    }

    public Optional<User> update(Integer id, User replacement) {
        return this.userRepository.findById(id)
                .map(user -> {
                    user.setEmail(replacement.getEmail());
                    user.setFirstName(replacement.getFirstName());
                    user.setLastName(replacement.getLastName());
                    user.setUsername(replacement.getUsername());
                    user.setPhone(replacement.getPhone());
                    return this.userRepository.save(user);
                });
    }

    public Optional<User> delete(Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            this.userRepository.delete(user.get());
        }
        return user;
    }
}
