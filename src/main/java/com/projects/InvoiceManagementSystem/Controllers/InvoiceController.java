package com.projects.InvoiceManagementSystem.Controllers;

import com.projects.InvoiceManagementSystem.DTO.InvoiceDTO;
import com.projects.InvoiceManagementSystem.Service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/invoice")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/getAll")
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/getBy{id}")
    public InvoiceDTO getInvoiceById(@PathVariable UUID id) {
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.createInvoice(invoiceDTO);
    }

    @PutMapping("/{invoiceId}")
    public InvoiceDTO updateInvoice(@PathVariable UUID invoiceId, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.updateInvoice(invoiceId, invoiceDTO);
    }

    @DeleteMapping("/{invoiceId}")
    public void deleteInvoice(@PathVariable UUID invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }
}
