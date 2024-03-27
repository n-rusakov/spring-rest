package com.example.springrest.web.controller;

import com.example.springrest.entity.User;
import com.example.springrest.mapper.UserMapper;
import com.example.springrest.service.impl.UserService;
import com.example.springrest.web.filter.UserFilter;
import com.example.springrest.web.model.UpsertUserRequest;
import com.example.springrest.web.model.UserListResponse;
import com.example.springrest.web.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll(
            @Valid UserFilter userFilter) {
        return ResponseEntity.ok(
                userMapper.userListToUserListResponse(
                        userService.findAllFiltered(userFilter))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.userToResponse(userService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<UserResponse> insert(@RequestBody @Valid UpsertUserRequest request) {
        User user = userService.insert(userMapper.requestToUser(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                userMapper.userToResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id, @RequestBody UpsertUserRequest request) {

        User user = userService.update(userMapper.requestToUser(id, request));

        return ResponseEntity.status(HttpStatus.OK).body(
                userMapper.userToResponse(user));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
