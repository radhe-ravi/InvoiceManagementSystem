package com.projects.InvoiceManagementSystem.repository;

import com.projects.InvoiceManagementSystem.entitiy.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDetailRepository extends JpaRepository<UserDetail, UUID> {

}
