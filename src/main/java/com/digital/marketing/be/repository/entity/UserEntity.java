package com.digital.marketing.be.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "SKILL")
    @Enumerated(EnumType.STRING)
    private Skill skill;

    @OneToMany(mappedBy = "manager")
    private List<ContractEntity> contracts;

    @OneToMany(mappedBy = "assignee")
    private List<TaskEntity> tasks;

    @ManyToOne
    @JoinColumn(name = "CONTRACT_ALLOCATION_ID")
    private ContractAllocation allocation;

    @Column(name = "SESSION_ID")
    private String sessionId;

    public UserEntity() {

    }

    public UserEntity(Long id, String email, String firstName, String lastName, String password, UserRole userRole, Skill skill,
                      List<ContractEntity> contracts, List<TaskEntity> tasks, ContractAllocation allocation, String sessionId) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRole = userRole;
        this.skill = skill;
        this.contracts = contracts;
        this.tasks = tasks;
        this.allocation = allocation;
        this.sessionId = sessionId;
    }

    public UserEntity(String email, String firstName, String lastName, String password, UserRole userRole, Skill skill,
                      List<ContractEntity> contracts, List<TaskEntity> tasks, ContractAllocation allocation, String sessionId) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRole = userRole;
        this.skill = skill;
        this.contracts = contracts;
        this.tasks = tasks;
        this.allocation = allocation;
        this.sessionId = sessionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public List<ContractEntity> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractEntity> contracts) {
        this.contracts = contracts;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
