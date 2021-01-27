package com.pabitero.booksdemo.service;

import com.pabitero.booksdemo.entity.User;
import com.pabitero.booksdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        User userInDB = userRepository.findById(user.getId()).orElse(null);
        userInDB.setFirstName(user.getFirstName());
        userInDB.setLastName(user.getLastName());
        userInDB.setEmail(user.getEmail());
        userInDB.setAddress(user.getAddress());
        userInDB.setUsername(user.getUsername());
        return userRepository.save(userInDB);
    }

    public String deleteUser(User user) {
        userRepository.delete(user);
        return "User #" + user.getId() + " was deleted.";
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    public void verifyLogin(User user) {
//        User userInDB = userRepository.findById(user.getId()).orElse(null);
//        if (userInDB != null) {
//
//        }
//    }
}
