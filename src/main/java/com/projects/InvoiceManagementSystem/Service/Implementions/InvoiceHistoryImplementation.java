package com.projects.InvoiceManagementSystem.Service;

import com.projects.InvoiceManagementSystem.DTO.InvoiceHistoryDTO;
import com.projects.InvoiceManagementSystem.Entities.InvoiceHistory;
import com.projects.InvoiceManagementSystem.Repository.InvoiceHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceHistoryImplementation implements InvoiceHistoryService {

    private final InvoiceHistoryRepository invoiceHistoryRepository;


    @Override
    public List<InvoiceHistoryDTO> getAllInvoiceHistories() {
        return invoiceHistoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceHistoryDTO getInvoiceHistoryById(UUID invoiceId) {
        return invoiceHistoryRepository.findById(invoiceId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Invoice history not found"));
    }

    @Override
    public InvoiceHistoryDTO saveInvoiceHistory(InvoiceHistoryDTO invoiceHistoryDTO) {
        return null;
    }

    private InvoiceHistoryDTO convertToDTO(InvoiceHistory invoiceHistory) {
        InvoiceHistoryDTO dto = new InvoiceHistoryDTO();
        dto.setInvoiceId(invoiceHistory.getInvoiceId());
        dto.setEvent(invoiceHistory.getEvent());
        dto.setCreatedOn(invoiceHistory.getCreatedOn());
        dto.setCreatedBy(invoiceHistory.getCreatedBy().getUserId());
        return dto;
    }
}
