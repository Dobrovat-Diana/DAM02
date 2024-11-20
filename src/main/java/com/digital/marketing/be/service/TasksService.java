package com.digital.marketing.be.service;

import com.digital.marketing.be.exception.NotFoundException;
import com.digital.marketing.be.exception.UnauthorizedException;
import com.digital.marketing.be.repository.TasksRepository;
import com.digital.marketing.be.repository.entity.TaskEntity;
import com.digital.marketing.be.repository.entity.TaskStatus;
import com.digital.marketing.be.repository.entity.UserEntity;
import com.digital.marketing.be.repository.entity.UserRole;
import com.digital.marketing.be.service.dto.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    public TaskEntity getTask(Long taskId) {
        return tasksRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task not found"));
    }

    public List<TaskEntity> getAllTasksForContract(Long contractId) {
        return tasksRepository.findByContractId(contractId);
    }

    public TaskEntity createTask(TaskEntity clientEntity, SecurityUser securityUser) {
        if (canCreateTask(securityUser)) {
            return tasksRepository.save(clientEntity);
        } else {
            throw new UnauthorizedException("You cannot create tasks");
        }
    }

    public void deleteTask(Long taskId, SecurityUser securityUser) {
        if (canDeleteTask(securityUser)) {
            tasksRepository.deleteById(taskId);
        } else {
            throw new UnauthorizedException("You cannot delete task");
        }
    }

    public TaskEntity updateTask(TaskEntity taskEntity, SecurityUser securityUser) {
        if (canEditTask(securityUser)) {
            return tasksRepository.save(taskEntity);
        } else {
            throw new UnauthorizedException("You cannot edit tasks");
        }
    }

    public TaskEntity changeTaskStatus(Long taskId, TaskStatus newStatus, SecurityUser securityUser) {
        TaskEntity taskEntity = tasksRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task not found"));
        if (canChangeTaskStatus(securityUser, taskEntity.getStatus(), newStatus)) {
            taskEntity.setStatus(newStatus);
            return tasksRepository.save(taskEntity);
        } else {
            throw new UnauthorizedException("You cannot change task status");
        }
    }

    private boolean canCreateTask(SecurityUser securityUser) {
        return ((UserEntity) securityUser.getPrincipal()).getUserRole() == UserRole.MANAGER;
    }

    private boolean canDeleteTask(SecurityUser securityUser) {
        return ((UserEntity) securityUser.getPrincipal()).getUserRole() == UserRole.MANAGER;
    }

    private boolean canEditTask(SecurityUser securityUser) {
        return ((UserEntity) securityUser.getPrincipal()).getUserRole() == UserRole.MANAGER;
    }

    private boolean canChangeTaskStatus(SecurityUser securityUser, TaskStatus oldStatus, TaskStatus newStatus) {
        UserRole userRole = ((UserEntity) securityUser.getPrincipal()).getUserRole();
        switch (userRole) {
            case MANAGER -> {
                return (
                        oldStatus == TaskStatus.UNDER_REVIEW && (newStatus == TaskStatus.COMPLETED || newStatus == TaskStatus.SKIPPED) ||
                                oldStatus == TaskStatus.NEW && newStatus == TaskStatus.WORK_IN_PROGRESS
                );
            }
            case EMPLOYEE -> {
                return (
                        oldStatus == TaskStatus.WORK_IN_PROGRESS && newStatus == TaskStatus.UNDER_REVIEW ||
                                oldStatus == TaskStatus.NEW && newStatus == TaskStatus.WORK_IN_PROGRESS
                );
            }
            default -> {
                return false;
            }
        }
    }

}
