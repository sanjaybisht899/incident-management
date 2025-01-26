package com.Incident.Management.Service;

import com.Incident.Management.Model.Incident;

import java.util.List;

public interface IncidentService {
    Incident createIncident(Incident incident); // Method to create a new incident
    Incident getIncidentById(Long id); // Method to get an incident by ID
    Incident getIncidentByIncidentId(String incidentId); // Method to get an incident by its unique incident ID
    List<Incident> getAllIncidents(); // Method to get all incidents
    Incident updateIncident(Long id, Incident incident, Long userId); // Method to update an incident
    void deleteIncident(Long id); // Method to delete an incident by ID
    List<Incident> getIncidentsByUserId(Long userId); // Method to get incidents by user ID
    Incident getIncidentByIdAndUserId(Long id, Long userId); // Method to get an incident by ID and user ID
    List<Incident> getIncidentsByPriority(String priority); // Method to get incidents by priority
    List<Incident> getIncidentsByStatus(String status); // Method to get incidents by status
}