package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.ContractType;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


class ContractTypeRepositoryTest extends StudyApplicationTests {

    @Autowired
    ContractTypeRepository contractTypeRepository;

    @Test
    public void create(){
        ContractType contractType = ContractType.builder()
                .type("근로계약서")
                .build();
        ContractType newContractType = contractTypeRepository.save(contractType);
        Assertions.assertNotNull(newContractType);
    }

    @Test
    void read(){
        List<ContractType> contractTypeList = contractTypeRepository.findAll();
        System.out.println("-----계약서 종류--------");
        for(ContractType c : contractTypeList){
            System.out.println(c);
        }
    }

    @Test
    void update(){
        Optional<ContractType> contractType = contractTypeRepository.findByIdx(9L);
        contractType.ifPresent(c ->{
            c.setType("임금계약서");
            contractTypeRepository.save(c);
        });
    }

    @Test
    @Transactional
    void delete(){
        Optional<ContractType> contractType = contractTypeRepository.findByIdx(9L);
        contractType.ifPresent(c ->{
            contractTypeRepository.deleteByIdx(9L);
        });

        Optional<ContractType> deleted = contractTypeRepository.findByIdx(9L);
        Assertions.assertFalse(deleted.isPresent());
    }


}