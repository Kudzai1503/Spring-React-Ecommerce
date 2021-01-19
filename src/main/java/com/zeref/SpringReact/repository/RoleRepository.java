package com.zeref.SpringReact.repository;

import java.util.Optional;

import com.zeref.SpringReact.model.ERole;
import com.zeref.SpringReact.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
