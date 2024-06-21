package com.projects.InvoiceManagementSystem.service.implementions;

import com.projects.InvoiceManagementSystem.dto.InvoiceItemDto;
import com.projects.InvoiceManagementSystem.entitiy.Invoice;
import com.projects.InvoiceManagementSystem.entitiy.InvoiceItem;
import com.projects.InvoiceManagementSystem.entitiy.UserDetail;
import com.projects.InvoiceManagementSystem.repository.InvoiceItemRepository;
import com.projects.InvoiceManagementSystem.service.InvoiceItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class InvoiceItemServiceImplementation implements InvoiceItemService {
    private final InvoiceItemRepository invoiceItemRepository;

    @Override
    public List<InvoiceItemDto> getAllInvoiceItems() {
        return invoiceItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList()
    }

    @Override
    public InvoiceItemDto getInvoiceItemById(UUID invoiceItemId) {
        return invoiceItemRepository.findById(invoiceItemId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Invoice item not found"));
    }

    @Override
    public InvoiceItemDto createInvoiceItem(InvoiceItemDto invoiceItemDTO) {

        log.info("Invoice item created {}", invoiceItemDTO);

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
        log.info(convertToDTO(invoiceItem).toString());
        return convertToDTO(invoiceItem);
    }

    @Override
    public InvoiceItemDto updateInvoiceItem(UUID invoiceItemId, InvoiceItemDto invoiceItemDTO) {

        log.info("Invoice item updated {}", invoiceItemDTO);

        InvoiceItem invoiceItem = invoiceItemRepository.findById(invoiceItemId)
                .orElseThrow(() -> new RuntimeException("Invoice item not found"));
        invoiceItem.setItemName(invoiceItemDTO.getItemName());
        invoiceItem.setItemDescription(invoiceItemDTO.getItemDescription());
        invoiceItemRepository.save(invoiceItem);

        log.info(convertToDTO(invoiceItem).toString());
        return convertToDTO(invoiceItem);
    }

    @Override
    public void deleteInvoiceItem(UUID invoiceItemId) {
        invoiceItemRepository.deleteById(invoiceItemId);
    }

    private InvoiceItemDto convertToDTO(InvoiceItem invoiceItem) {
        InvoiceItemDto dto = new InvoiceItemDto();
        dto.setInvoiceItemId(invoiceItem.getInvoiceItemId());
        dto.setInvoiceId(invoiceItem.getInvoice().getInvoiceId());
        dto.setItemName(invoiceItem.getItemName());
        dto.setItemDescription(invoiceItem.getItemDescription());
        dto.setCreatedOn(invoiceItem.getCreatedOn());
        dto.setCreatedBy(invoiceItem.getCreatedBy().getUserId());
        return dto;
    }
}
