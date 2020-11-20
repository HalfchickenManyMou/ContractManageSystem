package com.example.study.repository;

import com.example.study.model.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ContractRepository extends JpaRepository<Contract, Long> , JpaSpecificationExecutor<Contract> {

}
