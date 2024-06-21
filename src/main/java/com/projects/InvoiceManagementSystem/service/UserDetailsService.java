package com.projects.InvoiceManagementSystem.service;

import com.projects.InvoiceManagementSystem.dto.UserDetailDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserDetailsService {
    List<UserDetailDto> getAllUsers();
    UserDetailDto getUserById(UUID userId);
    UserDetailDto createUser(UserDetailDto userDetailDTO);
}
