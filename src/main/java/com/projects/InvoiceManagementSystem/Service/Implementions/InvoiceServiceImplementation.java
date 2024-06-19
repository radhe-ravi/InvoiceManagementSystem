package com.projects.InvoiceManagementSystem.Service.Implementions;

import com.projects.InvoiceManagementSystem.DTO.InvoiceDTO;
import com.projects.InvoiceManagementSystem.Service.InvoiceService;

import java.util.List;
import java.util.UUID;

public class InvoiceServiceImplemention implements InvoiceService {
    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return List.of();
    }

    @Override
    public InvoiceDTO getInvoiceById(UUID invoiceId) {
        return null;
    }

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        return null;
    }
}
