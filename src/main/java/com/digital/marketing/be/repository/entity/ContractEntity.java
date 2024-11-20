package com.digital.marketing.be.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Contracts")
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private UserEntity manager;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "MAN_DAYS")
    private Integer manDays;

    @OneToMany(mappedBy = "contract")
    private List<TaskEntity> tasks;

    @OneToOne
    @JoinColumn(name = "CONTRACT_ALLOCATION_ID")
    private ContractAllocation allocation;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    public ContractEntity() {
    }

    public ContractEntity(ClientEntity client, UserEntity manager,
                          LocalDateTime startDate, LocalDateTime endDate, Integer manDays, List<TaskEntity> tasks,
                          ContractAllocation allocation, ContractType contractType) {
        this.client = client;
        this.manager = manager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manDays = manDays;
        this.tasks = tasks;
        this.allocation = allocation;
        this.contractType = contractType;
    }

    public ContractEntity(Long id, ClientEntity client, UserEntity manager,
                          LocalDateTime startDate, LocalDateTime endDate, Integer manDays, List<TaskEntity> tasks,
                          ContractAllocation allocation, ContractType contractType) {
        this.id = id;
        this.client = client;
        this.manager = manager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.manDays = manDays;
        this.tasks = tasks;
        this.allocation = allocation;
        this.contractType = contractType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public UserEntity getManager() {
        return manager;
    }

    public void setManager(UserEntity manager) {
        this.manager = manager;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getManDays() {
        return manDays;
    }

    public void setManDays(Integer manDays) {
        this.manDays = manDays;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public ContractAllocation getAllocation() {
        return allocation;
    }

    public void setAllocation(ContractAllocation allocation) {
        this.allocation = allocation;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }
}
