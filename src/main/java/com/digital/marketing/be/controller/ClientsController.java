package com.digital.marketing.be.controller;


import com.digital.marketing.be.repository.entity.ClientEntity;
import com.digital.marketing.be.service.ClientService;
import com.digital.marketing.be.service.dto.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/clients")
public class ClientsController {

    private ClientService clientService;

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientEntity> getClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.getClient(clientId));
    }

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PostMapping
    public ResponseEntity<ClientEntity> createClient(@RequestBody ClientEntity clientEntity, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(clientService.createClient(clientEntity, securityUser));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity deleteClient(@PathVariable Long clientId, @Autowired SecurityUser securityUser) {
        clientService.deleteClient(clientId, securityUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientEntity> updateClient(@RequestBody ClientEntity clientEntity, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(clientService.updateClient(clientEntity, securityUser));
    }

}
