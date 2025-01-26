package com.Incident.Management.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incident")
public class IncidentController {

    @GetMapping("/test")
    public String testIncidentMap(){
        return "Incident Map is working fine";
    }

}
