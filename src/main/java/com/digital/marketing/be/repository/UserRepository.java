package com.digital.marketing.be.repository;

import com.digital.marketing.be.repository.entity.Skill;
import com.digital.marketing.be.repository.entity.UserEntity;
import com.digital.marketing.be.repository.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findBySessionId(String sessionId);

    Optional<UserEntity> findByEmailAndPasswordAndUserRole(String email, String password, UserRole userRole);

    List<UserEntity> findByUserRoleAndSkillAndAllocationIsNull(UserRole role, Skill skill);

}
