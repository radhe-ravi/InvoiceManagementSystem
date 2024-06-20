package com.projects.InvoiceManagementSystem.Service.Implementions;

import com.projects.InvoiceManagementSystem.DTO.UserDetailDTO;
import com.projects.InvoiceManagementSystem.Entities.UserDetail;
import com.projects.InvoiceManagementSystem.Repository.UserDetailRepository;
import com.projects.InvoiceManagementSystem.Service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserDetailsImplementation implements UserDetailsService {

    private final UserDetailRepository userDetailRepository;

    @Override
    public List<UserDetailDTO> getAllUsers() {
        return userDetailRepository.findAll().stream()
                .map(this::convertToDTO).toList();
    }

    @Override
    public UserDetailDTO getUserById(UUID userId) {
        return userDetailRepository.findById(userId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDetailDTO createUser(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(UUID.randomUUID());
        userDetail.setUserName(userDetailDTO.getUserName());
        userDetail.setCreatedOn(LocalDateTime.now());
        userDetail.setStatus(userDetailDTO.getStatus());
        userDetailRepository.save(userDetail);
        return convertToDTO(userDetail);
    }


    private UserDetailDTO convertToDTO(UserDetail userDetail) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setUserId(userDetail.getUserId());
        userDetailDTO.setUserName(userDetail.getUserName());
        userDetailDTO.setStatus(userDetail.getStatus());
        userDetailDTO.setCreatedOn(userDetail.getCreatedOn());
        return userDetailDTO;
    }
}
