package com.projects.InvoiceManagementSystem.Controllers;


import com.projects.InvoiceManagementSystem.DTO.UserDetailDTO;
import com.projects.InvoiceManagementSystem.Service.UserDetailsService;
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
    public List<UserDetailDTO> getAllUser() {
        return userDetailsService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDetailDTO getUserById(@PathVariable UUID id) {
       return userDetailsService.getUserById(id);
    }

    @PostMapping("/create")
    public UserDetailDTO createUser(@RequestBody UserDetailDTO userDetailDTO) {
        return userDetailsService.createUser(userDetailDTO);
    }
}
