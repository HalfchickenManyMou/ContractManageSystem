package com.example.study.repository;

import com.example.study.model.entity.Contract;
import com.example.study.model.entity.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface ContractRepository extends JpaRepository<Contract, Long> , JpaSpecificationExecutor<Contract> {

}
