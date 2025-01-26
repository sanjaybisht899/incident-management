package com.Incident.Management.Service;

import com.Incident.Management.Model.User;
import com.Incident.Management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder for encoding passwords

    @Override
    public ResponseEntity<?> generatePasswordResetToken(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            // Return a 404 Not Found response with an error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + email + " not found.");
        }

        // Generate the password reset token
        String token = UUID.randomUUID().toString();
        user.setPasswordResetToken(token);
        user.setPasswordResetTokenExpiry(LocalDateTime.now().plusHours(1)); // Token expires in 1 hour
        userRepository.save(user);

        // Send the token to the user's email (implementation not shown)
        sendPasswordResetEmail(user.getEmail(), token);

        // Return a 200 OK response with a success message
        return ResponseEntity.ok("Password reset token generated and sent to " + email);
    }

    @Override
    public ResponseEntity<?> resetPassword(String token, String newPassword) {
        User user = userRepository.findByPasswordResetToken(token);
        if (user != null && user.getPasswordResetTokenExpiry().isAfter(LocalDateTime.now())) {
            // Encode the new password before saving it
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setPasswordResetToken(null);
            user.setPasswordResetTokenExpiry(null);
            userRepository.save(user);

            // Return a 200 OK response with a success message
            return ResponseEntity.ok("Password reset successfully.");
        } else {
            // Return a 400 Bad Request response with an error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
        }
    }

    private void sendPasswordResetEmail(String email, String token) {
        // Implement email sending logic (e.g., using JavaMail or a third-party service)
        System.out.println("Sending password reset email to " + email + " with token: " + token);
    }
}