package com.Incident.Management.Repository;


import com.Incident.Management.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserName(String userName);
    User findByPasswordResetToken(String token); // Method to find a user by password reset token

}