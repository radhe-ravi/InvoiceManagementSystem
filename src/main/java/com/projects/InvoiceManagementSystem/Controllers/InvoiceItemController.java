package com.projects.InvoiceManagementSystem.Controllers;


import com.projects.InvoiceManagementSystem.DTO.InvoiceItemDTO;
import com.projects.InvoiceManagementSystem.Service.InvoiceItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("api/v1/invoice-item")
@AllArgsConstructor
public class InvoiceItemController {

    private final InvoiceItemService invoiceItemService;

    @GetMapping("/items")
    public List<InvoiceItemDTO> getAllInvoiceItems(){
        return invoiceItemService.getAllInvoiceItems();
    }

    @GetMapping("/{itemsId}")
    public InvoiceItemDTO getInvoiceItemById(@PathVariable UUID itemsId){
        return invoiceItemService.getInvoiceItemById(itemsId);
    }

    @PostMapping("/create-in-items")
    public InvoiceItemDTO createInvoiceItem(@RequestBody InvoiceItemDTO invoiceItemDTO){
        return invoiceItemService.createInvoiceItem(invoiceItemDTO);
    }

    @PutMapping("/{invoiceItemId}")
    public InvoiceItemDTO updateInvoiceItem(@PathVariable UUID invoiceItemId, @RequestBody InvoiceItemDTO invoiceItemDTO) {
        return invoiceItemService.updateInvoiceItem(invoiceItemId, invoiceItemDTO);
    }

    @DeleteMapping("/{invoiceItemId}")
    public void deleteInvoiceItem(@PathVariable UUID invoiceItemId) {
        invoiceItemService.deleteInvoiceItem(invoiceItemId);
    }

}
