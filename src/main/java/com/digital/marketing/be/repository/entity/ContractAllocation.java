package com.digital.marketing.be.repository.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CONTRACT_ALLOCATION")
public class ContractAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "allocation")
    private List<UserEntity> employees;

    @OneToOne(mappedBy = "allocation")
    private ContractEntity contractEntity;

    @Column(name = "NUMBER_OF_BE")
    private Integer numberOfBE;

    @Column(name = "NUMBER_OF_FE")
    private Integer numberOfFE;

    @Column(name = "NUMBER_OF_BA")
    private Integer numberOfBA;

    public ContractAllocation() {
    }

    public ContractAllocation(Long id, List<UserEntity> employees, ContractEntity contractEntity, Integer numberOfBE, Integer numberOfFE, Integer numberOfBA) {
        this.id = id;
        this.employees = employees;
        this.contractEntity = contractEntity;
        this.numberOfBE = numberOfBE;
        this.numberOfFE = numberOfFE;
        this.numberOfBA = numberOfBA;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<UserEntity> employees) {
        this.employees = employees;
    }

    public ContractEntity getContractEntity() {
        return contractEntity;
    }

    public void setContractEntity(ContractEntity contractEntity) {
        this.contractEntity = contractEntity;
    }

    public Integer getNumberOfBE() {
        return numberOfBE;
    }

    public void setNumberOfBE(Integer numberOfBE) {
        this.numberOfBE = numberOfBE;
    }

    public Integer getNumberOfFE() {
        return numberOfFE;
    }

    public void setNumberOfFE(Integer numberOfFE) {
        this.numberOfFE = numberOfFE;
    }

    public Integer getNumberOfBA() {
        return numberOfBA;
    }

    public void setNumberOfBA(Integer numberOfBA) {
        this.numberOfBA = numberOfBA;
    }
}
