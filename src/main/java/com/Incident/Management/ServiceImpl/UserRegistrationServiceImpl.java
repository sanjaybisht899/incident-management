package com.Incident.Management.Service;

import com.Incident.Management.DTO.UserRegistrationRequest;
import com.Incident.Management.Model.User;
import com.Incident.Management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder for encoding passwords

    @Override
    public ResponseEntity<?> registerUser(UserRegistrationRequest request) {
        // Check if the email is already registered
        if (userRepository.findByEmail(request.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already registered.");
        }

        // Create a new user
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encode the password
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setPinCode(request.getPinCode());
        user.setCity(request.getCity());
        user.setCountry(request.getCountry());

        // Save the user to the database
        userRepository.save(user);

        // Return a success response
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }
}