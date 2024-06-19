package com.projects.InvoiceManagementSystem.Repository;

import com.projects.InvoiceManagementSystem.Entities.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, UUID> {
}
