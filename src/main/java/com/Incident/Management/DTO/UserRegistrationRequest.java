package com.Incident.Management.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationRequest {
    // Getters and Setters
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String pinCode;
    private String city;
    private String country;

}