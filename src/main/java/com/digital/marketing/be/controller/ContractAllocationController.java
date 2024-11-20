package com.digital.marketing.be.controller;

import com.digital.marketing.be.repository.entity.ContractAllocation;
import com.digital.marketing.be.service.ContractAllocationService;
import com.digital.marketing.be.service.dto.ContractAllocationTemplateReq;
import com.digital.marketing.be.service.dto.ContractAllocationTemplateResp;
import com.digital.marketing.be.service.dto.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/contract_allocation")
public class ContractAllocationController {

    @Autowired
    private ContractAllocationService contractAllocationService;

    @PostMapping("/template")
    public ResponseEntity<ContractAllocationTemplateResp> getAllocationTemplate(@RequestBody ContractAllocationTemplateReq contractAllocationTemplateReq, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(contractAllocationService.getAllocationTemplate(contractAllocationTemplateReq, securityUser));
    }

    @PostMapping
    public ResponseEntity<ContractAllocation> createAllocation(@RequestBody ContractAllocation contractAllocation, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(contractAllocationService.createContractAllocation(contractAllocation, securityUser));
    }

    @PutMapping("/{allocationId}")
    public ResponseEntity<ContractAllocation> updateAllocation(@RequestBody ContractAllocation contractAllocation, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(contractAllocationService.updateAllocation(contractAllocation, securityUser));
    }

}
