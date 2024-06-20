package com.projects.InvoiceManagementSystem.DTOTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.projects.InvoiceManagementSystem.DTO.InvoiceItemDTO;

import java.util.UUID;

class InvoiceItemDTOTest {

    @Test
    void testInvoiceItemDTO() {
        UUID invoiceItemId = UUID.randomUUID();
        String itemName = "Item1";
        String itemDescription = "Description1";
        UUID invoiceId = UUID.randomUUID();
        UUID createdBy = UUID.randomUUID();

        InvoiceItemDTO invoiceItemDTO = new InvoiceItemDTO();
        invoiceItemDTO.setInvoiceItemId(invoiceItemId);
        invoiceItemDTO.setItemName(itemName);
        invoiceItemDTO.setItemDescription(itemDescription);
        invoiceItemDTO.setInvoiceId(invoiceId);
        invoiceItemDTO.setCreatedBy(createdBy);

        assertEquals(invoiceItemId, invoiceItemDTO.getInvoiceItemId());
        assertEquals(itemName, invoiceItemDTO.getItemName());
        assertEquals(itemDescription, invoiceItemDTO.getItemDescription());
        assertEquals(invoiceId, invoiceItemDTO.getInvoiceId());
        assertEquals(createdBy, invoiceItemDTO.getCreatedBy());
    }
}
