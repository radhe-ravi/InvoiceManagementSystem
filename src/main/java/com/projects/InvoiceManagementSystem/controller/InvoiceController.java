package com.projects.InvoiceManagementSystem.controller;

import com.projects.InvoiceManagementSystem.dto.InvoiceDto;
import com.projects.InvoiceManagementSystem.dto.InvoiceItemDto;
import com.projects.InvoiceManagementSystem.service.InvoiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/invoice")
@AllArgsConstructor
@Slf4j
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/getAll")
    public List<InvoiceDto> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/getBy/{id}")
    public InvoiceDto getInvoiceById(@PathVariable UUID id) {
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping
    public InvoiceDto createInvoice(@RequestBody InvoiceDto invoiceDTO) {
        log.info(invoiceDTO.toString());
        InvoiceDto updatedInvoice = invoiceService.createInvoice(invoiceDTO);
        log.info(updatedInvoice.toString());
        return updatedInvoice;
    }

    @PutMapping("/{invoiceId}")
    public InvoiceDto updateInvoice(@PathVariable UUID invoiceId, @RequestBody InvoiceDto invoiceDTO) {
        log.info(invoiceDTO.toString());
        InvoiceDto updatedInvoice = invoiceService.updateInvoice(invoiceId, invoiceDTO);
        log.info(updatedInvoice.toString());
        return updatedInvoice ;
    }

    @DeleteMapping("/{invoiceId}")
    public void deleteInvoice(@PathVariable UUID invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }
}
