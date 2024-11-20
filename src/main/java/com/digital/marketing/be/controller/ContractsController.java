package com.digital.marketing.be.controller;

import com.digital.marketing.be.repository.entity.ClientEntity;
import com.digital.marketing.be.repository.entity.ContractEntity;
import com.digital.marketing.be.service.ContractService;
import com.digital.marketing.be.service.dto.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/contracts")
public class ContractsController {

    private ContractService contractService;

    @GetMapping("/{contractId}")
    public ResponseEntity<ContractEntity> getContract(@PathVariable Long contractId) {
        return ResponseEntity.ok(contractService.getContract(contractId));
    }

    @GetMapping
    public ResponseEntity<List<ContractEntity>> getAllContracts() {
        return ResponseEntity.ok(contractService.getAllContracts());
    }

    @PostMapping
    public ResponseEntity<ContractEntity> createClient(@RequestBody ContractEntity contractEntity, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(contractService.createContract(contractEntity, securityUser));
    }

    @DeleteMapping("/{contractId}")
    public ResponseEntity deleteContract(@PathVariable Long contractId, @Autowired SecurityUser securityUser) {
        contractService.deleteContract(contractId, securityUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{contractId}")
    public ResponseEntity<ContractEntity> updateContract(@RequestBody ContractEntity contractEntity, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(contractService.updateContract(contractEntity, securityUser));
    }

    @PutMapping("/{contractId}/manager/{managerId}")
    public ResponseEntity allocateManager(@PathVariable Long contractId, @PathVariable Long managerId, @Autowired SecurityUser securityUser) {
        contractService.allocateManager(contractId, managerId, securityUser);
        return ResponseEntity.ok().build();
    }

}
