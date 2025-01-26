package com.Incident.Management.Service;

import com.Incident.Management.Model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
    User getUserByUserName(String userName);
    void deleteUser(Long id);
}