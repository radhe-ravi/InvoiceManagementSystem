package com.projects.InvoiceManagementSystem.service.implementions;

import com.projects.InvoiceManagementSystem.dto.InvoiceDto;
import com.projects.InvoiceManagementSystem.entitiy.Invoice;
import com.projects.InvoiceManagementSystem.entitiy.UserDetail;
import com.projects.InvoiceManagementSystem.repository.InvoiceRepository;
import com.projects.InvoiceManagementSystem.service.InvoiceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Service
@Slf4j
public class InvoiceServiceImplementation implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDto> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public InvoiceDto getInvoiceById(UUID invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    @Override
    public InvoiceDto createInvoice(InvoiceDto invoiceDTO) {
        log.info(invoiceDTO.toString());

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

        log.info(convertToDTO(invoice).toString());
        return convertToDTO(invoice);
    }

    @Override
    public InvoiceDto updateInvoice(UUID invoiceId, InvoiceDto invoiceDTO) {

        log.info(invoiceDTO.toString());

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setInvoiceNo(invoiceDTO.getInvoiceNo());
        invoice.setPanNo(invoiceDTO.getPanNo());
        invoice.setGstNo(invoiceDTO.getGstNo());
        invoiceRepository.save(invoice);

        log.info(convertToDTO(invoice).toString());

        return convertToDTO(invoice);
    }

    @Override
    public void deleteInvoice(UUID invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }


    private InvoiceDto convertToDTO(Invoice invoice) {
        InvoiceDto dto = new InvoiceDto();
        dto.setInvoiceId(invoice.getInvoiceId());
        dto.setInvoiceNo(invoice.getInvoiceNo());
        dto.setPanNo(invoice.getPanNo());
        dto.setGstNo(invoice.getGstNo());
        dto.setCreatedOn(invoice.getCreatedOn());
        dto.setCreatedBy(invoice.getCreatedBy().getUserId());
        return dto;
    }
}
