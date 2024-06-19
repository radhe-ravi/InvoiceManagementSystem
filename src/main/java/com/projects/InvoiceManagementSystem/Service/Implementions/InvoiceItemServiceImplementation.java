package com.projects.InvoiceManagementSystem.Service.Implementions;

import com.projects.InvoiceManagementSystem.DTO.InvoiceItemDTO;
import com.projects.InvoiceManagementSystem.Entities.Invoice;
import com.projects.InvoiceManagementSystem.Entities.InvoiceItem;
import com.projects.InvoiceManagementSystem.Entities.UserDetail;
import com.projects.InvoiceManagementSystem.Repository.InvoiceItemRepository;
import com.projects.InvoiceManagementSystem.Service.InvoiceItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceItemServiceImplementation implements InvoiceItemService {
    private final InvoiceItemRepository invoiceItemRepository;

    @Override
    public List<InvoiceItemDTO> getAllInvoiceItems() {
        return invoiceItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceItemDTO getInvoiceItemById(UUID invoiceItemId) {
        return invoiceItemRepository.findById(invoiceItemId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Invoice item not found"));
    }

    @Override
    public InvoiceItemDTO createInvoiceItem(InvoiceItemDTO invoiceItemDTO) {
        InvoiceItem invoiceItem = new InvoiceItem();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(invoiceItemDTO.getCreatedBy());
        invoiceItem.setInvoiceItemId(UUID.randomUUID());
        invoiceItem.setInvoice(new Invoice(invoiceItemDTO.getInvoiceId()));
        invoiceItem.setItemName(invoiceItemDTO.getItemName());
        invoiceItem.setItemDescription(invoiceItemDTO.getItemDescription());
        invoiceItem.setCreatedOn(LocalDateTime.now());
        invoiceItem.setCreatedBy(userDetail);
        invoiceItemRepository.save(invoiceItem);
        return convertToDTO(invoiceItem);
    }

    @Override
    public InvoiceItemDTO updateInvoiceItem(UUID invoiceItemId, InvoiceItemDTO invoiceItemDTO) {
        InvoiceItem invoiceItem = invoiceItemRepository.findById(invoiceItemId)
                .orElseThrow(() -> new RuntimeException("Invoice item not found"));
        invoiceItem.setItemName(invoiceItemDTO.getItemName());
        invoiceItem.setItemDescription(invoiceItemDTO.getItemDescription());
        invoiceItemRepository.save(invoiceItem);
        return convertToDTO(invoiceItem);
    }

    @Override
    public void deleteInvoiceItem(UUID invoiceItemId) {
        invoiceItemRepository.deleteById(invoiceItemId);
    }

    private InvoiceItemDTO convertToDTO(InvoiceItem invoiceItem) {
        InvoiceItemDTO dto = new InvoiceItemDTO();
        dto.setInvoiceItemId(invoiceItem.getInvoiceItemId());
        dto.setInvoiceId(invoiceItem.getInvoice().getInvoiceId());
        dto.setItemName(invoiceItem.getItemName());
        dto.setItemDescription(invoiceItem.getItemDescription());
        dto.setCreatedOn(invoiceItem.getCreatedOn());
        dto.setCreatedBy(invoiceItem.getCreatedBy().getUserId());
        return dto;
    }
}
