package com.projects.InvoiceManagementSystem.controller;

import com.projects.InvoiceManagementSystem.dto.InvoiceHistoryDto;
import com.projects.InvoiceManagementSystem.service.InvoiceHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("api/v1/invoiceHistory")
@AllArgsConstructor
@Slf4j
public class InvoiceHistoryController {

    private final InvoiceHistoryService invoiceHistoryService;

    @GetMapping("/getAll")
    List<InvoiceHistoryDto> getAllInvoiceHistories(){
        log.info("getAllInvoiceHistories {}", invoiceHistoryService.getAllInvoiceHistories());
        return invoiceHistoryService.getAllInvoiceHistories();
    }

    @GetMapping("/{historyId}")
    public InvoiceHistoryDto getInvoiceHistoryById(@PathVariable UUID historyId){
        return invoiceHistoryService.getInvoiceHistoryById(historyId);
    }

    @DeleteMapping("/{invoiceId}")
    public void deleteInvoiceHistory(@PathVariable UUID invoiceId) {
        invoiceHistoryService.deleteInvoiceHistory(invoiceId);
    }
}
