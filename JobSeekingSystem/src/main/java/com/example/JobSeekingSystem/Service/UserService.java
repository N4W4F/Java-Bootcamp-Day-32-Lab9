package com.example.JobSeekingSystem.Service;

import com.example.JobSeekingSystem.Model.User;
import com.example.JobSeekingSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        if (userRepository.findAll().isEmpty())
            return null;

        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id, User user) {
        User oldUser = findUserById(id);
        if (oldUser == null) return false;

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id) {
        User oldUser = findUserById(id);
        if (oldUser == null) return false;

        userRepository.delete(oldUser);
        return true;
    }

    private User findUserById(Integer id) {
        for (User u : userRepository.findAll())
            if (u.getId().equals(id))
                return u;

        return null;
    }
}
