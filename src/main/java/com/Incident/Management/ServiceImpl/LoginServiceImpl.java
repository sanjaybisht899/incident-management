package com.Incident.Management.Service;

import com.Incident.Management.DTO.LoginRequest;
import com.Incident.Management.DTO.LoginResponse;
import com.Incident.Management.Model.User;
import com.Incident.Management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new RuntimeException("User not found.");
        }

        // Check if the password matches
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password.");
        }

        // Create a response
        LoginResponse response = new LoginResponse();
        response.setMessage("Login successful!");
        // Optionally, generate and include a JWT token here
        return response;
    }
}