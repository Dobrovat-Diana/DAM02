package com.digital.marketing.be.controller;

import com.digital.marketing.be.repository.entity.Skill;
import com.digital.marketing.be.repository.entity.UserEntity;
import com.digital.marketing.be.repository.entity.UserRole;
import com.digital.marketing.be.service.UserService;
import com.digital.marketing.be.service.dto.LoginReq;
import com.digital.marketing.be.service.dto.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.digital.marketing.be.service.dto.Constants.SESSION_ID_KEY;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody LoginReq loginReq) {
        String userEmail = loginReq.getUserEmail();
        String password = loginReq.getPassword();
        UserRole userRole =  loginReq.getUserRole();

        String sessionId = userService.authenticate(userEmail, password, userRole);

        if(sessionId != null) {
            HttpHeaders headers = new HttpHeaders();
            String cookie = SESSION_ID_KEY + "=" + sessionId +"; Path=/; SameSite=Strict";
            headers.add(HttpHeaders.SET_COOKIE, cookie);
            return ResponseEntity.ok().headers(headers).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong username or password");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(userService.createUser(userEntity, securityUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId, @Autowired SecurityUser securityUser) {
        userService.deleteUser(userId, securityUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity userEntity, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(userService.updateUser(userEntity, securityUser));
    }

    @GetMapping("/employees/unallocated")
    public ResponseEntity<List<UserEntity>> getAllEmployeesWithSkillAndUnallocated(@RequestParam Skill skill, @Autowired SecurityUser securityUser) {
        return ResponseEntity.ok(userService.getAllEmployeesWithSkillAndUnallocated(skill, securityUser));
    }

}
