package com.projects.InvoiceManagementSystem.Service;

import com.projects.InvoiceManagementSystem.DTO.UserDetailDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserDetailsService {
    List<UserDetailDTO> getAllUsers();
    UserDetailDTO getUserById(UUID userId);
    UserDetailDTO createUser(UserDetailDTO userDetailDTO);
}
