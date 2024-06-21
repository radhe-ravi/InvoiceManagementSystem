package com.projects.InvoiceManagementSystem.service.implementions;

import com.projects.InvoiceManagementSystem.dto.InvoiceHistoryDto;
import com.projects.InvoiceManagementSystem.entitiy.InvoiceHistory;
import com.projects.InvoiceManagementSystem.entitiy.UserDetail;
import com.projects.InvoiceManagementSystem.repository.InvoiceHistoryRepository;
import com.projects.InvoiceManagementSystem.service.InvoiceHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public InvoiceHistoryDto saveInvoiceHistory(InvoiceHistoryDto invoiceHistoryDTO) {

        log.info("Invoice history : {}", invoiceHistoryDTO);

        InvoiceHistory invoiceHistory = new InvoiceHistory();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(invoiceHistoryDTO.getCreatedBy());
        userDetail.setUserId(invoiceHistoryDTO.getCreatedBy());
        invoiceHistory.setInvoiceId(UUID.randomUUID());
        invoiceHistory.setEvent(invoiceHistoryDTO.getEvent());
        invoiceHistory.setCreatedOn(LocalDateTime.now());
        invoiceHistory.setCreatedBy(userDetail);

        log.info("Invoice history saved : {}", convertToDTO(invoiceHistory));
        invoiceHistoryRepository.save(invoiceHistory);
        return convertToDTO(invoiceHistory);
    }

    @Override
    public InvoiceHistoryDto updateInvoiceHistory(UUID invoiceId, InvoiceHistoryDto invoiceHistoryDTO) {

        log.info("Invoice history before : {}", invoiceHistoryDTO);

        InvoiceHistory invoiceHistory = invoiceHistoryRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice history not found"));
        invoiceHistory.setEvent(invoiceHistoryDTO.getEvent());
        invoiceHistoryRepository.save(invoiceHistory);

        log.info("Invoice history after : {}", convertToDTO(invoiceHistory));
        return convertToDTO(invoiceHistory);
    }

    @Override
    public void deleteInvoiceHistory(UUID invoiceId) {
        invoiceHistoryRepository.deleteById(invoiceId);
    }

    private InvoiceHistoryDto convertToDTO(InvoiceHistory invoiceHistory) {
        InvoiceHistoryDto dto = new InvoiceHistoryDto();
        dto.setInvoiceId(invoiceHistory.getInvoiceId());
        dto.setEvent(invoiceHistory.getEvent());
        dto.setCreatedOn(invoiceHistory.getCreatedOn());
        dto.setCreatedBy(invoiceHistory.getCreatedBy().getUserId());
        return dto;
    }
}
