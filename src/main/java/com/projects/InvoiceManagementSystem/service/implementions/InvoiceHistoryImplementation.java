package com.projects.InvoiceManagementSystem.service.implementions;

import com.projects.InvoiceManagementSystem.dto.InvoiceHistoryDto;

import com.projects.InvoiceManagementSystem.entitiy.InvoiceHistory;
import com.projects.InvoiceManagementSystem.repository.InvoiceHistoryRepository;
import com.projects.InvoiceManagementSystem.service.InvoiceHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class InvoiceHistoryImplementation implements InvoiceHistoryService {

    private final InvoiceHistoryRepository invoiceHistoryRepository;


    @Override
    public List<InvoiceHistoryDto> getAllInvoiceHistories() {
        return invoiceHistoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public InvoiceHistoryDto getInvoiceHistoryById(UUID invoiceId) {
        return invoiceHistoryRepository.findById(invoiceId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Invoice history not found"));
    }

    @Override
    public void deleteInvoiceHistory(UUID invoiceId) {
        invoiceHistoryRepository.deleteById(invoiceId);
    }

    private InvoiceHistoryDto convertToDTO(InvoiceHistory invoiceHistory) {
        InvoiceHistoryDto invoiceHistoryDto = new InvoiceHistoryDto();
        invoiceHistoryDto.setInvoiceHistoryId(invoiceHistory.getInvoiceId());
        invoiceHistoryDto.setInvoiceId(invoiceHistory.getInvoiceId());
        invoiceHistoryDto.setEvent(invoiceHistory.getEvent());
        invoiceHistoryDto.setCreatedBy(invoiceHistory.getCreatedBy().getUserId());
        invoiceHistoryDto.setCreatedOn(invoiceHistory.getCreatedOn());
        return invoiceHistoryDto;
    }

}
