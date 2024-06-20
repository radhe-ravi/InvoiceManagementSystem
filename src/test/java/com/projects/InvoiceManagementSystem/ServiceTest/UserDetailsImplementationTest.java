package com.projects.InvoiceManagementSystem.ServiceTest;

import com.projects.InvoiceManagementSystem.DTO.UserDetailDTO;
import com.projects.InvoiceManagementSystem.Entities.UserDetail;
import com.projects.InvoiceManagementSystem.Repository.UserDetailRepository;
import com.projects.InvoiceManagementSystem.Service.Implementions.UserDetailsImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserDetailsImplementationTest {
    @Mock
    private UserDetailRepository userDetailRepository;

    @InjectMocks
    private UserDetailsImplementation userDetailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testGetAllUsers() {
        List<UserDetail> userList = new ArrayList<>();
        UserDetail user = new UserDetail();
        user.setUserId(UUID.randomUUID());
        user.setUserName("Radhe Ravi");
        user.setStatus("Active");
        userList.add(user);

        when(userDetailRepository.findAll()).thenReturn(userList);

        List<UserDetailDTO> result = userDetailService.getAllUsers();
        assertEquals(1, result.size());
        assertEquals("Radhe Ravi", result.get(0).getUserName());
        assertEquals("Active", result.get(0).getStatus());
    }

    @Test
    void testGetUserById() {
        UUID USER_ID = UUID.randomUUID();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(USER_ID);
        userDetail.setUserName("Radhe Ravi");
        userDetail.setStatus("Active");

        when(userDetailRepository.findById(USER_ID)).thenReturn(Optional.of(userDetail));

        UserDetailDTO result = userDetailService.getUserById(USER_ID);
        assertEquals("Radhe Ravi", result.getUserName());
        assertEquals("Active", result.getStatus());

    }

    @Test
    void createUserTest(){
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setUserName("Radhe Ravi");
        userDetailDTO.setStatus("Offline");

        when(userDetailRepository.save(any(UserDetail.class))).thenReturn(new UserDetail());

        UserDetailDTO result = userDetailService.createUser(userDetailDTO);
        assertNotNull(result.getUserId());
    }

}
