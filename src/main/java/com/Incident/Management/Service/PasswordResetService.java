package com.Incident.Management.Service;

import org.springframework.http.ResponseEntity;

public interface PasswordResetService {
    ResponseEntity<?> generatePasswordResetToken(String email); // Return ResponseEntity
    ResponseEntity<?> resetPassword(String token, String newPassword); // Return ResponseEntity
}