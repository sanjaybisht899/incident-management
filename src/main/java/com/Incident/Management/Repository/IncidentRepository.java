package com.Incident.Management.Repository;

import com.Incident.Management.Model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Incident findByIncidentId(String incidentId);
    List<Incident> findByUserId(Long userId);
    Optional<Incident> findByIdAndUserId(Long id, Long userId);
    List<Incident> findByPriority(String priority);
    List<Incident> findByStatus(String status);
}