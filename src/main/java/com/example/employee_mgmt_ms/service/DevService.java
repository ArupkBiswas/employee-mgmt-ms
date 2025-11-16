package com.example.employee_mgmt_ms.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DevService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String baseUrl = "http://localhost:8200/v1/my-secret/data/secret";

    public Object getDbSecret(String vaultToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Vault-Token", vaultToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl+"/dbCreds",
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }

    public Object getJwtSecret(String vaultToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Vault-Token", vaultToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl+"/jwt",
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
}
