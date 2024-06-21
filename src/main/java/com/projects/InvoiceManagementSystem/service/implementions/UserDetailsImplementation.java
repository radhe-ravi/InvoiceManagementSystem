package com.projects.InvoiceManagementSystem.service.implementions;

import com.projects.InvoiceManagementSystem.dto.UserDetailDto;
import com.projects.InvoiceManagementSystem.entitiy.UserDetail;
import com.projects.InvoiceManagementSystem.repository.UserDetailRepository;
import com.projects.InvoiceManagementSystem.service.UserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class UserDetailsImplementation implements UserDetailsService {

    private final UserDetailRepository userDetailRepository;

    @Override
    public List<UserDetailDto> getAllUsers() {
        return userDetailRepository.findAll().stream()
                .map(this::convertToDTO).toList();
    }

    @Override
    public UserDetailDto getUserById(UUID userId) {
        return userDetailRepository.findById(userId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDetailDto createUser(UserDetailDto userDetailDTO) {
        log.info("Creating user: {}", userDetailDTO);
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(UUID.randomUUID());
        userDetail.setUserName(userDetailDTO.getUserName());
        userDetail.setCreatedOn(LocalDateTime.now());
        userDetail.setStatus(userDetailDTO.getStatus());
        userDetailRepository.save(userDetail);
        log.info("User created: {}", convertToDTO(userDetail));
        return convertToDTO(userDetail);
    }


    private UserDetailDto convertToDTO(UserDetail userDetail) {
        UserDetailDto userDetailDTO = new UserDetailDto();
        userDetailDTO.setUserId(userDetail.getUserId());
        userDetailDTO.setUserName(userDetail.getUserName());
        userDetailDTO.setStatus(userDetail.getStatus());
        userDetailDTO.setCreatedOn(userDetail.getCreatedOn());
        return userDetailDTO;
    }
}
