package com.example.study.repository;

import com.example.study.model.entity.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {

    Optional<ContractType> findByIdx(Long idx);
    void deleteByIdx(Long idx);
}
