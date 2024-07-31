package com.api.clinica.controllers.v1;

import com.api.clinica.domain.user.User;
import com.api.clinica.domain.user.UserDTO;
import com.api.clinica.services.v1.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users")
public class UserController {
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable UUID id){
        return ResponseEntity.ok().body(this.userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDTO payload){
        return ResponseEntity.status(201).body(this.userService.create(payload));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
