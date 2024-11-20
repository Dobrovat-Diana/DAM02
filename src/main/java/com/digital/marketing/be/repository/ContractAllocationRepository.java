package com.digital.marketing.be.repository;

import com.digital.marketing.be.repository.entity.ContractAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractAllocationRepository extends JpaRepository<ContractAllocation, Long> {
}
