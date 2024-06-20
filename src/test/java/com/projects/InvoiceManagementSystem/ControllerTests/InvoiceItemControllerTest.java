package com.projects.InvoiceManagementSystem.ControllerTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import com.projects.InvoiceManagementSystem.Service.InvoiceItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.InvoiceManagementSystem.Controllers.InvoiceItemController;
import com.projects.InvoiceManagementSystem.DTO.InvoiceItemDTO;

import java.util.UUID;

@WebMvcTest(InvoiceItemController.class)
 class InvoiceItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceItemService invoiceItemService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateInvoiceItem() throws Exception {
        InvoiceItemDTO invoiceItemDTO = new InvoiceItemDTO();
        invoiceItemDTO.setItemName("Item1");
        invoiceItemDTO.setItemDescription("Description1");
        UUID invoiceId = UUID.randomUUID();
        UUID createdBy = UUID.randomUUID();
        invoiceItemDTO.setInvoiceId(invoiceId);
        invoiceItemDTO.setCreatedBy(createdBy);

        InvoiceItemDTO savedInvoiceItemDTO = new InvoiceItemDTO();
        savedInvoiceItemDTO.setInvoiceItemId(UUID.randomUUID());
        savedInvoiceItemDTO.setItemName("Item1");
        savedInvoiceItemDTO.setItemDescription("Description1");
        savedInvoiceItemDTO.setInvoiceId(invoiceId);
        savedInvoiceItemDTO.setCreatedBy(createdBy);

        when(invoiceItemService.createInvoiceItem(any(InvoiceItemDTO.class))).thenReturn(savedInvoiceItemDTO);

        mockMvc.perform(post("/invoice-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceItemDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.itemName").value("Item1"))
                .andExpect(jsonPath("$.itemDescription").value("Description1"))
                .andExpect(jsonPath("$.invoiceId").value(invoiceId.toString()))
                .andExpect(jsonPath("$.createdBy").value(createdBy.toString()));

        verify(invoiceItemService, times(1)).createInvoiceItem(any(InvoiceItemDTO.class));
    }

    @Test
    void testUpdateInvoiceItem() throws Exception {
        UUID invoiceItemId = UUID.randomUUID();
        InvoiceItemDTO invoiceItemDTO = new InvoiceItemDTO();
        invoiceItemDTO.setItemName("UpdatedItem");
        invoiceItemDTO.setItemDescription("UpdatedDescription");
        UUID invoiceId = UUID.randomUUID();
        UUID createdBy = UUID.randomUUID();
        invoiceItemDTO.setInvoiceId(invoiceId);
        invoiceItemDTO.setCreatedBy(createdBy);

        InvoiceItemDTO updatedInvoiceItemDTO = new InvoiceItemDTO();
        updatedInvoiceItemDTO.setInvoiceItemId(invoiceItemId);
        updatedInvoiceItemDTO.setItemName("UpdatedItem");
        updatedInvoiceItemDTO.setItemDescription("UpdatedDescription");
        updatedInvoiceItemDTO.setInvoiceId(invoiceId);
        updatedInvoiceItemDTO.setCreatedBy(createdBy);

        when(invoiceItemService.updateInvoiceItem(eq(invoiceItemId), any(InvoiceItemDTO.class))).thenReturn(updatedInvoiceItemDTO);

        mockMvc.perform(put("/invoice-items/{id}", invoiceItemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoiceItemDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value("UpdatedItem"))
                .andExpect(jsonPath("$.itemDescription").value("UpdatedDescription"))
                .andExpect(jsonPath("$.invoiceId").value(invoiceId.toString()))
                .andExpect(jsonPath("$.createdBy").value(createdBy.toString()));

        verify(invoiceItemService, times(1)).updateInvoiceItem(eq(invoiceItemId), any(InvoiceItemDTO.class));
    }
}
