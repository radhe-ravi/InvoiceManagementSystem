package com.projects.InvoiceManagementSystem.Service.Implementions;

import com.projects.InvoiceManagementSystem.DTO.InvoiceHistoryDTO;
import com.projects.InvoiceManagementSystem.Entities.InvoiceHistory;
import com.projects.InvoiceManagementSystem.Entities.UserDetail;
import com.projects.InvoiceManagementSystem.Repository.InvoiceHistoryRepository;
import com.projects.InvoiceManagementSystem.Service.InvoiceHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceHistoryImplementation implements InvoiceHistoryService {

    private final InvoiceHistoryRepository invoiceHistoryRepository;


    @Override
    public List<InvoiceHistoryDTO> getAllInvoiceHistories() {
        return invoiceHistoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public InvoiceHistoryDTO getInvoiceHistoryById(UUID invoiceId) {
        return invoiceHistoryRepository.findById(invoiceId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Invoice history not found"));
    }

    @Override
    public InvoiceHistoryDTO saveInvoiceHistory(InvoiceHistoryDTO invoiceHistoryDTO) {
        InvoiceHistory invoiceHistory = new InvoiceHistory();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(invoiceHistoryDTO.getCreatedBy());

        userDetail.setUserId(invoiceHistoryDTO.getCreatedBy());
        invoiceHistory.setInvoiceId(UUID.randomUUID());
        invoiceHistory.setEvent(invoiceHistoryDTO.getEvent());
        invoiceHistory.setCreatedOn(LocalDateTime.now());
        invoiceHistory.setCreatedBy(userDetail);
        invoiceHistoryRepository.save(invoiceHistory);
        return convertToDTO(invoiceHistory);
    }

    @Override
    public InvoiceHistoryDTO updateInvoiceHistory(UUID invoiceId, InvoiceHistoryDTO invoiceHistoryDTO) {
        InvoiceHistory invoiceHistory = invoiceHistoryRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice history not found"));
        invoiceHistory.setEvent(invoiceHistoryDTO.getEvent());
        invoiceHistoryRepository.save(invoiceHistory);
        return convertToDTO(invoiceHistory);
    }

    @Override
    public void deleteInvoiceHistory(UUID invoiceId) {
        invoiceHistoryRepository.deleteById(invoiceId);
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
