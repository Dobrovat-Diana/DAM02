package com.digital.marketing.be.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.UUID;

@Entity
@Table(name = "Tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "CONTRACT_ID")
    private ContractEntity contract;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity assignee;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public TaskEntity() {

    }

    public TaskEntity(String name, ContractEntity contract, UserEntity assignee, TaskStatus status) {
        this.name = name;
        this.contract = contract;
        this.assignee = assignee;
        this.status = status;
    }

    public TaskEntity(Long id, String name, ContractEntity contract, UserEntity assignee, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.contract = contract;
        this.assignee = assignee;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public UserEntity getAssignee() {
        return assignee;
    }

    public void setAssignee(UserEntity assignee) {
        this.assignee = assignee;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
