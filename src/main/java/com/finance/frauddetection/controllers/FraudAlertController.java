package com.finance.frauddetection.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fraud-alerts")
public class FraudAlertController {
    @GetMapping
    public String getAll(){
        return "alert";
    }
    @GetMapping("/open")
    public String getOpenAlerts(){
        return "open alerts";
    }
    @PutMapping("/{id}/status")
    public void updateStatus(){

    }
}
