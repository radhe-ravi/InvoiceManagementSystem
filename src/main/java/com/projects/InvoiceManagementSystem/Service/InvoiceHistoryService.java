package com.projects.InvoiceManagementSystem.Service;

import com.projects.InvoiceManagementSystem.DTO.InvoiceHistoryDTO;

import java.util.List;
import java.util.UUID;

public interface InvoiceHistoryService {
    List<InvoiceHistoryDTO> getAllInvoiceHistories();
    InvoiceHistoryDTO getInvoiceHistoryById(UUID id);
    InvoiceHistoryDTO saveInvoiceHistory(InvoiceHistoryDTO invoiceHistoryDTO);
    InvoiceHistoryDTO updateInvoiceHistory(UUID invoiceId, InvoiceHistoryDTO invoiceHistoryDTO);
    void deleteInvoiceHistory(UUID invoiceId);
}
