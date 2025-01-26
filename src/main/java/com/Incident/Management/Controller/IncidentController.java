package com.Incident.Management.Controller;

import com.Incident.Management.Model.Incident;
import com.Incident.Management.Service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    // Create a new incident
    @PostMapping
    public ResponseEntity<?> createIncident(@RequestBody Incident incident) {
        try {
            Incident createdIncident = incidentService.createIncident(incident);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIncident);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Get an incident by ID
    @GetMapping("/{id}")
    public Incident getIncidentById(@PathVariable Long id) {
        return incidentService.getIncidentById(id);
    }

    // Get an incident by its unique incident ID
    @GetMapping("/incidentId/{incidentId}")
    public Incident getIncidentByIncidentId(@PathVariable String incidentId) {
        return incidentService.getIncidentByIncidentId(incidentId);
    }

    // Get all incidents
    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }

    // Update an incident
    @PutMapping("/{id}/user/{userId}")
    public ResponseEntity<?> updateIncident(@PathVariable Long id, @PathVariable Long userId, @RequestBody Incident incident) {
        Incident updatedIncident = incidentService.updateIncident(id, incident, userId);
        if (updatedIncident != null) {
            return ResponseEntity.ok(updatedIncident);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to edit this incident or the incident is closed.");
        }
    }

    // Delete an incident by ID
    @DeleteMapping("/{id}")
    public void deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
    }

    // Get all incidents for a specific user
    @GetMapping("/user/{userId}")
    public List<Incident> getIncidentsByUserId(@PathVariable Long userId) {
        return incidentService.getIncidentsByUserId(userId);
    }

    // Get an incident by ID and user ID
    @GetMapping("/{id}/user/{userId}")
    public Incident getIncidentByIdAndUserId(@PathVariable Long id, @PathVariable Long userId) {
        return incidentService.getIncidentByIdAndUserId(id, userId);
    }

    // Get all incidents by priority
    @GetMapping("/priority/{priority}")
    public List<Incident> getIncidentsByPriority(@PathVariable String priority) {
        return incidentService.getIncidentsByPriority(priority);
    }

    // Get all incidents by status
    @GetMapping("/status/{status}")
    public List<Incident> getIncidentsByStatus(@PathVariable String status) {
        return incidentService.getIncidentsByStatus(status);
    }
}