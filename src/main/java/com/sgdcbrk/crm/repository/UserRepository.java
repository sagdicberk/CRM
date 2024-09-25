package com.sgdcbrk.crm.repository;

import com.sgdcbrk.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
