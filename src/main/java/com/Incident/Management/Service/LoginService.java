package com.Incident.Management.Service;

import com.Incident.Management.DTO.LoginRequest;
import com.Incident.Management.DTO.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest request);
}