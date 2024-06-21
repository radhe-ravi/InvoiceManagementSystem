package com.projects.InvoiceManagementSystem.service;

import com.projects.InvoiceManagementSystem.dto.InvoiceItemDto;

import java.util.List;
import java.util.UUID;

public interface InvoiceItemService {

    List<InvoiceItemDto> getAllInvoiceItems();
    InvoiceItemDto getInvoiceItemById(UUID invoiceItemId);
    InvoiceItemDto createInvoiceItem(InvoiceItemDto invoiceItemDTO);
    InvoiceItemDto updateInvoiceItem(UUID invoiceItemId, InvoiceItemDto invoiceItemDTO);
    void deleteInvoiceItem(UUID invoiceItemId);
}
