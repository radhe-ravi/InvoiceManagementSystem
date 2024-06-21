package com.projects.InvoiceManagementSystem.repository;

import com.projects.InvoiceManagementSystem.entitiy.InvoiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceHistoryRepository extends JpaRepository<InvoiceHistory, UUID> {

}
