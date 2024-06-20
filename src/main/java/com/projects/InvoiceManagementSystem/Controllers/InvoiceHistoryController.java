package com.projects.InvoiceManagementSystem.Controllers;

import com.projects.InvoiceManagementSystem.DTO.InvoiceHistoryDTO;
import com.projects.InvoiceManagementSystem.Service.InvoiceHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/invoiceHistory")
@AllArgsConstructor
public class InvoiceHistoryController {

    private final InvoiceHistoryService invoiceHistoryService;

    @GetMapping("/create")
    List<InvoiceHistoryDTO> getAllInvoiceHistories(){
        return invoiceHistoryService.getAllInvoiceHistories();
    }

    @GetMapping("/{hisId}")
    public InvoiceHistoryDTO getInvoiceHistoryById(@PathVariable UUID hisId){
        return invoiceHistoryService.getInvoiceHistoryById(hisId);
    }

    @PostMapping("/save")
    public InvoiceHistoryDTO saveInvoiceHistory(InvoiceHistoryDTO invoiceHistoryDTO){
        return invoiceHistoryService.saveInvoiceHistory(invoiceHistoryDTO);
    }

    @DeleteMapping("/{invoiceId}")
    public void deleteInvoiceHistory(@PathVariable UUID invoiceId) {
        invoiceHistoryService.deleteInvoiceHistory(invoiceId);
    }
}
