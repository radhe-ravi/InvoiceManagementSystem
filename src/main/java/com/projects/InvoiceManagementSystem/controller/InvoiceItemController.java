package com.projects.InvoiceManagementSystem.controller;


import com.projects.InvoiceManagementSystem.dto.InvoiceDto;
import com.projects.InvoiceManagementSystem.dto.InvoiceItemDto;
import com.projects.InvoiceManagementSystem.service.InvoiceItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/invoice-item")
@AllArgsConstructor
@Slf4j
public class InvoiceItemController {

    private final InvoiceItemService invoiceItemService;


    @GetMapping("/items")
    public List<InvoiceItemDto> getAllInvoiceItems(){
        return invoiceItemService.getAllInvoiceItems();
    }

    @GetMapping("/{itemsId}")
    public InvoiceItemDto getInvoiceItemById(@PathVariable UUID itemsId){
        return invoiceItemService.getInvoiceItemById(itemsId);
    }

    @PostMapping("/createInvoiceItems")
    public InvoiceItemDto createInvoiceItem(@RequestBody InvoiceItemDto invoiceItemDTO){
        log.info("getInvoiceItemById: {}" , invoiceItemDTO);
        log.info("createInvoiceItem: {}" , invoiceItemService.createInvoiceItem(invoiceItemDTO));
        return invoiceItemService.createInvoiceItem(invoiceItemDTO);
    }

    @PutMapping("/{invoiceItemId}")
    public InvoiceItemDto updateInvoiceItem(@PathVariable UUID invoiceItemId, @RequestBody InvoiceItemDto invoiceItemDTO) {
       log.info("Incoming Dto {}", invoiceItemDTO);
       log.info("updateInvoiceItem:{}",invoiceItemService.updateInvoiceItem(invoiceItemId, invoiceItemDTO));
        return invoiceItemService.updateInvoiceItem(invoiceItemId, invoiceItemDTO);
    }

    @DeleteMapping("/{invoiceItemId}")
    public void deleteInvoiceItem(@PathVariable UUID invoiceItemId) {
        invoiceItemService.deleteInvoiceItem(invoiceItemId);
    }

}
