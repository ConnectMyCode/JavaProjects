package com.supporttriage.repository;

import com.supporttriage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}


/**
 * Repository layer for User entity.
 * Provides database access using Spring Data JPA.
 *
 * Extends JpaRepository:
 * - gives built-in CRUD operations (save, findById, delete, etc.)
 *
 * Custom methods:
 * - findByEmail(): used during login to fetch user
 * - existsByEmail(): used during signup to prevent duplicate users
 *   
 * No implementation needed — Spring generates it automatically.
 */