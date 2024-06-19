package com.projects.InvoiceManagementSystem.Repository;

import com.projects.InvoiceManagementSystem.Entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}
