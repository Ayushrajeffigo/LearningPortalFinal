package com.effigo.learningPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.effigo.learningPortal.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRoleType(String roletype);
}