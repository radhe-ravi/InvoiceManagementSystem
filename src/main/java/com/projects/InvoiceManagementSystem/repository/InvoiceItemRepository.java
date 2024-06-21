package com.projects.InvoiceManagementSystem.repository;

import com.projects.InvoiceManagementSystem.entitiy.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, UUID> {
}
