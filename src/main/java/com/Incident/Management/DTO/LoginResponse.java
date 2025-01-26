package com.Incident.Management.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    // Getters and Setters
    private String message;
    private String token; // Optional: Include a JWT token for authentication

}