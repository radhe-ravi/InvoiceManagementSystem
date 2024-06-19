package com.projects.InvoiceManagementSystem.Repository;

import com.projects.InvoiceManagementSystem.Entities.InvoiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceHistoryRepository extends JpaRepository<InvoiceHistory, UUID> {

}
