package com.example.study.repository;

import com.example.study.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByIdx(long idx);
    void deleteByIdx(Long idx);
}
