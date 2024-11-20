package com.digital.marketing.be.service;

import com.digital.marketing.be.exception.NotFoundException;
import com.digital.marketing.be.exception.UnauthorizedException;
import com.digital.marketing.be.repository.ClientRepository;
import com.digital.marketing.be.repository.entity.ClientEntity;
import com.digital.marketing.be.repository.entity.UserEntity;
import com.digital.marketing.be.repository.entity.UserRole;
import com.digital.marketing.be.service.dto.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientEntity getClient(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("Client not found"));
    }

    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    public ClientEntity createClient(ClientEntity clientEntity, SecurityUser securityUser) {
        if(canCreateClient(securityUser)) {
            return clientRepository.save(clientEntity);
        } else {
            throw new UnauthorizedException("You cannot create clients");
        }
    }

    public void deleteClient(Long clientId, SecurityUser securityUser) {
        if(canDeleteClient(securityUser)) {
            clientRepository.deleteById(clientId);
        } else {
            throw new UnauthorizedException("You cannot delete clients");
        }
    }

    public ClientEntity updateClient(ClientEntity clientEntity, SecurityUser securityUser) {
        if(canEditClient(securityUser)) {
            return clientRepository.save(clientEntity);
        } else {
            throw new UnauthorizedException("You cannot edit clients");
        }
    }

    private boolean canCreateClient(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.ADMIN;
    }

    private boolean canDeleteClient(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.ADMIN;
    }

    private boolean canEditClient(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.ADMIN;
    }

}
