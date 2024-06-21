package com.projects.InvoiceManagementSystem.repository;

import com.projects.InvoiceManagementSystem.entitiy.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}
