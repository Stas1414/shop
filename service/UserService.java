package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserService  {

    private UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void save(User user){
        userRepository.save(user);
    }
    public User getUserByUsername(String username){
        return userRepository.findUsersByUsername(username);
    }

    public List<User> getAllUsersByRole(String role){
        return userRepository.findUsersByRoleList(role);
    }


}
