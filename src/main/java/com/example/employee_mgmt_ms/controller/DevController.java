package com.example.employee_mgmt_ms.controller;

import com.example.employee_mgmt_ms.service.DevService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dev")
public class DevController {

    public final DevService devService ;

    public DevController(DevService devService) {
        this.devService = devService;
    }

    @GetMapping(value="/vault/secret/db", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDbSecret(@RequestHeader("X-Vault-Token") String vaultToken) {
        Object secret = devService.getDbSecret(vaultToken);
        return ResponseEntity.ok(secret);
    }

    @GetMapping(value="/vault/secret/jwt", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getJwtSecret(@RequestHeader("X-Vault-Token") String vaultToken) {
        Object secret = devService.getJwtSecret(vaultToken);
        return ResponseEntity.ok(secret);
    }
}
