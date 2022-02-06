package com.govil.services;

import com.govil.models.User;
import com.govil.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String name, String phoneNumber, String password) {
        User user = User.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .hashedPassword(password)
                .build();
        return userRepository.save(user);
    }

    public boolean updatePassword(Long userId, String newPassword) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new RuntimeException();
        }
        User userObj = user.get();
        userObj.setHashedPassword(newPassword);
        User updateUser = userRepository.save(userObj);
        return true;
    }
}
