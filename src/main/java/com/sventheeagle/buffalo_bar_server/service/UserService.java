package com.sventheeagle.buffalo_bar_server.service;

import com.sventheeagle.buffalo_bar_server.model.User;
import com.sventheeagle.buffalo_bar_server.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(@NonNull String id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User does not exist."));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
