package com.projects.InvoiceManagementSystem.Repository;

import com.projects.InvoiceManagementSystem.Entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDetailRepository extends JpaRepository<UserDetail, UUID> {
}
