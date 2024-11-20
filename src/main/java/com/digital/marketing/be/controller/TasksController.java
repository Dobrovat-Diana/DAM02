package com.digital.marketing.be.controller;

import com.digital.marketing.be.repository.entity.ClientEntity;
import com.digital.marketing.be.repository.entity.TaskEntity;
import com.digital.marketing.be.repository.entity.TaskStatus;
import com.digital.marketing.be.service.TasksService;
import com.digital.marketing.be.service.dto.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tasks")
public class TasksController {

    private TasksService tasksService;

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskEntity> getTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(tasksService.getTask(taskId));
    }

    @GetMapping
    public ResponseEntity<List<TaskEntity>> getAllTasksForContract(@RequestParam Long contractId) {
        return ResponseEntity.ok(tasksService.getAllTasksForContract(contractId));
    }

    @PostMapping
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity taskEntity, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(tasksService.createTask(taskEntity, securityUser));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable Long taskId, @Autowired SecurityUser securityUser) {
        tasksService.deleteTask(taskId, securityUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskEntity> updateTask(@RequestBody TaskEntity taskEntity, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(tasksService.updateTask(taskEntity, securityUser));
    }

    @PutMapping("/{taskId}/status/{taskStatus}")
    public ResponseEntity<TaskEntity> changeTaskStatus(@PathVariable Long taskId, @PathVariable TaskStatus taskStatus, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(tasksService.changeTaskStatus(taskId, taskStatus, securityUser));
    }

}
