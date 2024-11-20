package com.digital.marketing.be.service;

import com.digital.marketing.be.exception.NotFoundException;
import com.digital.marketing.be.exception.UnauthorizedException;
import com.digital.marketing.be.repository.UserRepository;
import com.digital.marketing.be.repository.entity.ClientEntity;
import com.digital.marketing.be.repository.entity.Skill;
import com.digital.marketing.be.repository.entity.UserEntity;
import com.digital.marketing.be.repository.entity.UserRole;
import com.digital.marketing.be.service.dto.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserBySessionId(String sessionId) {
        return userRepository.findBySessionId(sessionId).orElse(null);
    }

    public String authenticate(String userEmail, String password, UserRole userRole) {
        Optional<UserEntity> userEntityOpt = userRepository.findByEmailAndPasswordAndUserRole(userEmail, password, userRole);
        String sessionId = null;
        if(userEntityOpt.isPresent()) {
            sessionId = UUID.randomUUID().toString();
            UserEntity userEntity = userEntityOpt.get();
            userEntity.setSessionId(sessionId);
            userRepository.save(userEntity);
        }

        return sessionId;
    }

    public UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity createUser(UserEntity userEntity, SecurityUser securityUser) {
        if(canCreateUser(securityUser)) {
            return userRepository.save(userEntity);
        } else {
            throw new UnauthorizedException("You cannot create users");
        }
    }

    public void deleteUser(Long userId, SecurityUser securityUser) {
        if(canDeleteUser(securityUser)) {
            userRepository.deleteById(userId);
        } else {
            throw new UnauthorizedException("You cannot delete users");
        }
    }

    public UserEntity updateUser(UserEntity userEntity, SecurityUser securityUser) {
        if(canEditUser(securityUser)) {
            return userRepository.save(userEntity);
        } else {
            throw new UnauthorizedException("You cannot edit users");
        }
    }

    public List<UserEntity> getAllEmployeesWithSkillAndUnallocated(Skill skill, SecurityUser securityUser) {
        if(canSeeEmployeesWithSkillAndUnallocated(securityUser)) {
            return userRepository.findByUserRoleAndSkillAndAllocationIsNull(UserRole.EMPLOYEE, skill);
        } else {
            throw new UnauthorizedException("You cannot see employees");
        }
    }

    private boolean canCreateUser(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.ADMIN;
    }

    private boolean canDeleteUser(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.ADMIN;
    }

    private boolean canEditUser(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.ADMIN;
    }

    private boolean canSeeEmployeesWithSkillAndUnallocated(SecurityUser securityUser) {
        return ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.ADMIN ||
                ((UserEntity)securityUser.getPrincipal()).getUserRole() == UserRole.MANAGER;
    }
}
