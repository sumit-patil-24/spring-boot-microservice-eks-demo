package com.example.user_service.services;

import com.example.user_service.models.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setUsername(user.getUsername());
        return userRepository.save(createdUser);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
