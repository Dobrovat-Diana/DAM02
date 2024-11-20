package com.digital.marketing.be.repository;

import com.digital.marketing.be.repository.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByContractId(Long contractId);

}
