package com.projects.InvoiceManagementSystem.service;

import com.projects.InvoiceManagementSystem.dto.InvoiceHistoryDto;

import java.util.List;
import java.util.UUID;

public interface InvoiceHistoryService {
    List<InvoiceHistoryDto> getAllInvoiceHistories();
    InvoiceHistoryDto getInvoiceHistoryById(UUID id);
    InvoiceHistoryDto saveInvoiceHistory(InvoiceHistoryDto invoiceHistoryDTO);
    InvoiceHistoryDto updateInvoiceHistory(UUID invoiceId, InvoiceHistoryDto invoiceHistoryDTO);
    void deleteInvoiceHistory(UUID invoiceId);
}
