package com.Incident.Management.Service;

import com.Incident.Management.DTO.UserRegistrationRequest;
import org.springframework.http.ResponseEntity;

public interface UserRegistrationService {
    ResponseEntity<?> registerUser(UserRegistrationRequest request);
}