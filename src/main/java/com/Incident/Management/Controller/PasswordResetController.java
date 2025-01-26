package com.Incident.Management.Controller;

import com.Incident.Management.DTO.PasswordResetRequest;
import com.Incident.Management.Service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generatePasswordResetToken(@RequestParam String email) {
        return passwordResetService.generatePasswordResetToken(email);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest request) {
        return passwordResetService.resetPassword(request.getToken(), request.getNewPassword());
    }
}