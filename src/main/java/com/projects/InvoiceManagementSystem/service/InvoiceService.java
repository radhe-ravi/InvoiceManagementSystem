package com.projects.InvoiceManagementSystem.service;
import com.projects.InvoiceManagementSystem.dto.InvoiceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface InvoiceService {
    List<InvoiceDto> getAllInvoices();
    InvoiceDto getInvoiceById(UUID invoiceId);
    InvoiceDto createInvoice(InvoiceDto invoiceDTO);
    InvoiceDto updateInvoice(UUID invoiceId, InvoiceDto invoiceDTO);
    void deleteInvoice(UUID invoiceId);
}
