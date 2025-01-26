package com.Incident.Management.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Incident {

    // Getters and Setters (use Lombok @Data if you prefer)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String incidentId; // Auto-generated in the format RMG + Random 5-digit number + Current year

    @Column(nullable = false)
    private String reporterName; // Name of the user who logs in and creates the incident

    @Column(nullable = false)
    private String incidentDetails; // Text field for incident details

    @Column(nullable = false)
    private LocalDateTime reportedDateTime; // Date and time when the incident was reported

    @Column(nullable = false)
    private String priority; // Dropdown with values: High, Medium, Low

    @Column(nullable = false)
    private String status; // Dropdown with values: Open, In Progress, Closed

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // The user who created the incident

}