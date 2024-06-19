package com.projects.InvoiceManagementSystem.Service;

import com.projects.InvoiceManagementSystem.DTO.InvoiceItemDTO;
import com.projects.InvoiceManagementSystem.Entities.InvoiceItem;

import java.util.List;
import java.util.UUID;

public interface InvoiceItemService {

    List<InvoiceItemDTO> getAllInvoiceItems();
    InvoiceItemDTO getInvoiceItemById(UUID invoiceItemId);
    InvoiceItemDTO createInvoiceItem(InvoiceItemDTO invoiceItemDTO);
    InvoiceItemDTO updateInvoiceItem(UUID invoiceItemId, InvoiceItemDTO invoiceItemDTO);
    void deleteInvoiceItem(UUID invoiceItemId);
}
