package com.projects.InvoiceManagementSystem.controller;


import com.projects.InvoiceManagementSystem.dto.UserDetailDto;
import com.projects.InvoiceManagementSystem.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/vi/users")
public class UserDetailController {
    private final UserDetailsService userDetailsService;

    @GetMapping("/get-all")
    public List<UserDetailDto> getAllUser() {
        return userDetailsService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDetailDto getUserById(@PathVariable UUID id) {
       return userDetailsService.getUserById(id);
    }

    @PostMapping("/create")
    public UserDetailDto createUser(@RequestBody UserDetailDto userDetailDTO) {
        return userDetailsService.createUser(userDetailDTO);
    }
}
