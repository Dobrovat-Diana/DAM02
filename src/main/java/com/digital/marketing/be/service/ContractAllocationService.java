package com.digital.marketing.be.service;

import com.digital.marketing.be.exception.NotFoundException;
import com.digital.marketing.be.exception.UnauthorizedException;
import com.digital.marketing.be.repository.ContractAllocationRepository;
import com.digital.marketing.be.repository.ContractRepository;
import com.digital.marketing.be.repository.entity.ContractAllocation;
import com.digital.marketing.be.repository.entity.ContractType;
import com.digital.marketing.be.repository.entity.UserEntity;
import com.digital.marketing.be.repository.entity.UserRole;
import com.digital.marketing.be.service.dto.ContractAllocationTemplateReq;
import com.digital.marketing.be.service.dto.ContractAllocationTemplateResp;
import com.digital.marketing.be.service.dto.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractAllocationService {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ContractAllocationRepository contractAllocationRepository;

    public ContractAllocationTemplateResp getAllocationTemplate(ContractAllocationTemplateReq contractAllocationTemplateReq, SecurityUser securityUser) {
        if(canAllocateResources(securityUser)) {
            Integer manDays = contractAllocationTemplateReq.getManDays();
            ContractType contractType = contractAllocationTemplateReq.getContractType();
            switch (contractType) {
                case TYPE1 -> {
                    if (manDays < 10) {
                        return new ContractAllocationTemplateResp(3, 2, 1);
                    } else if (manDays > 10 && manDays < 25) {
                        return new ContractAllocationTemplateResp(5, 4, 2);
                    } else {
                        return new ContractAllocationTemplateResp(8, 5, 3);
                    }
                }
                case TYPE2 -> {
                    if (manDays < 10) {
                        return new ContractAllocationTemplateResp(4, 3, 1);
                    } else if (manDays > 10 && manDays < 25) {
                        return new ContractAllocationTemplateResp(6, 4, 2);
                    } else {
                        return new ContractAllocationTemplateResp(9, 5, 3);
                    }
                }
                case TYPE3 -> {
                    if (manDays < 10) {
                        return new ContractAllocationTemplateResp(1, 3, 1);
                    } else if (manDays > 10 && manDays < 25) {
                        return new ContractAllocationTemplateResp(2, 4, 2);
                    } else {
                        return new ContractAllocationTemplateResp(3, 5, 3);
                    }
                }
                case TYPE4 -> {
                    if (manDays < 10) {
                        return new ContractAllocationTemplateResp(2, 2, 2);
                    } else if (manDays > 10 && manDays < 25) {
                        return new ContractAllocationTemplateResp(3, 3, 3);
                    } else {
                        return new ContractAllocationTemplateResp(4, 4, 4);
                    }
                }
                default -> throw new NotFoundException("Contract type not found");
            }
        } else {
            throw new UnauthorizedException("You cannot allocate resources");
        }
    }

    public ContractAllocation createContractAllocation(ContractAllocation contractAllocation, SecurityUser securityUser) {
        if(canCreateAllocation(securityUser)) {
            return contractAllocationRepository.save(contractAllocation);
        } else {
            throw new UnauthorizedException("You cannot create allocation");
        }
    }

    public ContractAllocation updateAllocation(ContractAllocation contractAllocation, SecurityUser securityUser) {
        if(canUpdateAllocation(securityUser)) {
            return contractAllocationRepository.save(contractAllocation);
        } else {
            throw new UnauthorizedException("You cannot update allocation");
        }
    }

    private boolean canAllocateResources(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.MANAGER;
    }

    private boolean canCreateAllocation(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.MANAGER;
    }

    private boolean canUpdateAllocation(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.MANAGER;
    }

}
