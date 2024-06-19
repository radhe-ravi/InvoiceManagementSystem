package com.projects.InvoiceManagementSystem.Service.Implementions;

import com.projects.InvoiceManagementSystem.DTO.InvoiceDTO;
import com.projects.InvoiceManagementSystem.Entities.Invoice;
import com.projects.InvoiceManagementSystem.Entities.UserDetail;
import com.projects.InvoiceManagementSystem.Repository.InvoiceRepository;
import com.projects.InvoiceManagementSystem.Service.InvoiceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class InvoiceServiceImplementation implements InvoiceService {
    private InvoiceRepository invoiceRepository;


    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoiceById(UUID invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        UserDetail ud = new UserDetail();
        ud.setUserId(invoiceDTO.getCreatedBy());
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID());
        invoice.setInvoiceNo(invoiceDTO.getInvoiceNo());
        invoice.setPanNo(invoiceDTO.getPanNo());
        invoice.setGstNo(invoiceDTO.getGstNo());
        invoice.setCreatedOn(LocalDateTime.now());
        invoice.setCreatedBy(ud);
        invoiceRepository.save(invoice);
        return convertToDTO(invoice);
    }

    @Override
    public InvoiceDTO updateInvoice(UUID invoiceId, InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setInvoiceNo(invoiceDTO.getInvoiceNo());
        invoice.setPanNo(invoiceDTO.getPanNo());
        invoice.setGstNo(invoiceDTO.getGstNo());
        invoiceRepository.save(invoice);
        return convertToDTO(invoice);
    }

    @Override
    public void deleteInvoice(UUID invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }


    private InvoiceDTO convertToDTO(Invoice invoice) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setInvoiceId(invoice.getInvoiceId());
        dto.setInvoiceNo(invoice.getInvoiceNo());
        dto.setPanNo(invoice.getPanNo());
        dto.setGstNo(invoice.getGstNo());
        dto.setCreatedOn(invoice.getCreatedOn());
        dto.setCreatedBy(invoice.getCreatedBy().getUserId());
        return dto;
    }
}
