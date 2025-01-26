package com.Incident.Management.Service;

import com.Incident.Management.Model.Incident;
import com.Incident.Management.Model.User;
import com.Incident.Management.Repository.IncidentRepository;
import com.Incident.Management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Incident createIncident(Incident incident) {
        // Check if the user exists
        User user = userRepository.findById(incident.getUser().getId()).orElse(null);
        if (user == null) {
            throw new RuntimeException("User with ID " + incident.getUser().getId() + " does not exist.");
        }

        // Generate the incident ID
        String incidentId;
        do {
            incidentId = "RMG" + String.format("%05d", new Random().nextInt(100000)) + LocalDateTime.now().getYear();
        } while (incidentRepository.findByIncidentId(incidentId) != null); // Ensure the ID is unique

        // Set the user and other fields
        incident.setUser(user);
        incident.setIncidentId(incidentId);
        incident.setReportedDateTime(LocalDateTime.now());

        // Save the incident to the database
        return incidentRepository.save(incident);
    }

    @Override
    public Incident getIncidentById(Long id) {
        return incidentRepository.findById(id).orElse(null);
    }

    @Override
    public Incident getIncidentByIncidentId(String incidentId) {
        return incidentRepository.findByIncidentId(incidentId);
    }

    @Override
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    @Override
    public Incident updateIncident(Long id, Incident incident, Long userId) {
        Incident existingIncident = incidentRepository.findByIdAndUserId(id, userId).orElse(null);
        if (existingIncident != null && !"Closed".equals(existingIncident.getStatus())) {
            existingIncident.setIncidentDetails(incident.getIncidentDetails());
            existingIncident.setPriority(incident.getPriority());
            existingIncident.setStatus(incident.getStatus());
            return incidentRepository.save(existingIncident);
        }
        return null; // Return null if the incident is closed, not found, or the user is not the creator
    }

    @Override
    public void deleteIncident(Long id) {
        incidentRepository.deleteById(id);
    }

    @Override
    public List<Incident> getIncidentsByUserId(Long userId) {
        return incidentRepository.findByUserId(userId);
    }

    @Override
    public Incident getIncidentByIdAndUserId(Long id, Long userId) {
        return incidentRepository.findByIdAndUserId(id, userId).orElse(null);
    }

    @Override
    public List<Incident> getIncidentsByPriority(String priority) {
        return incidentRepository.findByPriority(priority);
    }

    @Override
    public List<Incident> getIncidentsByStatus(String status) {
        return incidentRepository.findByStatus(status);
    }
}