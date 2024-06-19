package com.projects.InvoiceManagementSystem.Service;
import com.projects.InvoiceManagementSystem.DTO.InvoiceDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface InvoiceService {
    List<InvoiceDTO> getAllInvoices();
    InvoiceDTO getInvoiceById(UUID invoiceId);
    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO updateInvoice(UUID invoiceId, InvoiceDTO invoiceDTO);
    void deleteInvoice(UUID invoiceId);
}
