package com.digital.marketing.be.service;

import com.digital.marketing.be.exception.NotFoundException;
import com.digital.marketing.be.exception.UnauthorizedException;
import com.digital.marketing.be.repository.ContractRepository;
import com.digital.marketing.be.repository.UserRepository;
import com.digital.marketing.be.repository.entity.ContractEntity;
import com.digital.marketing.be.repository.entity.UserEntity;
import com.digital.marketing.be.repository.entity.UserRole;
import com.digital.marketing.be.service.dto.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private UserRepository userRepository;

    public ContractEntity getContract(Long contractId) {
        return contractRepository.findById(contractId).orElseThrow(() -> new NotFoundException("Contract not found"));
    }

    public List<ContractEntity> getAllContracts() {
        return contractRepository.findAll();
    }

    public ContractEntity createContract(ContractEntity contractEntity, SecurityUser securityUser) {
        if(canCreateContract(securityUser)) {
            return contractRepository.save(contractEntity);
        } else {
            throw new UnauthorizedException("You cannot create contracts");
        }
    }

    public void deleteContract(Long contractId, SecurityUser securityUser) {
        if(canDeleteContract(securityUser)) {
            contractRepository.deleteById(contractId);
        } else {
            throw new UnauthorizedException("You cannot delete contracts");
        }
    }

    public ContractEntity updateContract(ContractEntity contractEntity, SecurityUser securityUser) {
        if(canEditContract(securityUser)) {
            return contractRepository.save(contractEntity);
        } else {
            throw new UnauthorizedException("You cannot edit contracts");
        }
    }

    public void allocateManager(Long contractId, Long managerId, SecurityUser securityUser) {
        if(canAllocateManager(securityUser)) {
            ContractEntity contract = contractRepository.findById(contractId).orElseThrow(() -> new NotFoundException("Contract not found"));
            UserEntity manager = userRepository.findById(managerId).orElseThrow(() -> new NotFoundException("Manager not found"));
            if (manager.getUserRole() != UserRole.MANAGER) {
                throw new NotFoundException("Manager not found");
            }
            contract.setManager(manager);
            contractRepository.save(contract);
        } else {
            throw new UnauthorizedException("You cannot allocate manager");
        }
    }

    private boolean canCreateContract(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.ADMIN;
    }

    private boolean canDeleteContract(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.ADMIN;
    }

    private boolean canEditContract(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.ADMIN;
    }

    private boolean canAllocateManager(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.ADMIN;
    }
}
