package com.projects.InvoiceManagementSystem.ServiceTest;

import com.projects.InvoiceManagementSystem.DTO.InvoiceHistoryDTO;
import com.projects.InvoiceManagementSystem.Entities.InvoiceHistory;
import com.projects.InvoiceManagementSystem.Entities.UserDetail;
import com.projects.InvoiceManagementSystem.Repository.InvoiceHistoryRepository;
import com.projects.InvoiceManagementSystem.Service.Implementions.InvoiceHistoryImplementation;
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
import static org.mockito.Mockito.*;

class InvoiceHistoryImplementationTest {

    @Mock
    private InvoiceHistoryRepository invoiceRepository;

    @InjectMocks
    private InvoiceHistoryImplementation invoiceHistoryImplementation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetInvoiceHistory() {
        UUID id = UUID.randomUUID();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserName("Radhe Ravi");
        userDetail.setUserId(id);


        List<InvoiceHistory> invoiceHistoryList = new ArrayList<>();
        InvoiceHistory invoiceHistory = new InvoiceHistory();
        invoiceHistory.setInvoiceId(id);
        invoiceHistory.setEvent("Created");
        invoiceHistory.setCreatedBy(userDetail);
        invoiceHistoryList.add(invoiceHistory);

        when(invoiceRepository.findAll()).thenReturn(invoiceHistoryList);

        List<InvoiceHistoryDTO> result = invoiceHistoryImplementation.getAllInvoiceHistories();
        assertEquals(1, result.size());
        assertEquals("Created", result.get(0).getEvent());
    }



    @Test
    void testGetInvoiceHistoryById(){
        UUID id = UUID.randomUUID();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserName("Radhe Ravi");
        userDetail.setUserId(id);

        UUID invoiceId = UUID.randomUUID();
        InvoiceHistory invoiceHistory = new InvoiceHistory();
        invoiceHistory.setInvoiceId(invoiceId);
        invoiceHistory.setEvent("Event1");
        invoiceHistory.setCreatedBy(userDetail);

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoiceHistory));

        InvoiceHistoryDTO result = invoiceHistoryImplementation.getInvoiceHistoryById(invoiceId);
        assertEquals("Event1", result.getEvent());

    }

    @Test
    void testDeleteInvoiceHistory() {
        UUID invoiceId = UUID.randomUUID();

        doNothing().when(invoiceRepository).deleteById(invoiceId);

        invoiceHistoryImplementation.deleteInvoiceHistory(invoiceId);

        verify(invoiceRepository, times(1)).deleteById(invoiceId);
    }
}
