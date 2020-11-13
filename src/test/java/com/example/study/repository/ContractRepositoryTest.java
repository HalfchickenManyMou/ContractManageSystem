package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Contract;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

public class ContractRepositoryTest extends StudyApplicationTests {

    @Autowired
    ContractRepository contractRepository;

    @Test
    public void create(){
        Contract contract = new Contract();
        contract.setIdx(2L);
        contract.setCode("code");
        contract.setName("기미선");
        contract.setContractTypeIdx(2L);
        contract.setUserCode("meme");
        contract.setDepartmentIdx(4L);
        contract.setContent("content");
        contract.setOwnerName("김희선");
        contract.setOwnerBusinessNumber("bnum");
        contract.setOtherAddress("addr");
        contract.setOtherName("rlagmltjs");
        contract.setOtherBusinessNumber("otherbnum");
        contract.setOtherAddress("otheraddr");
        contract.setStartDate(Date.valueOf("2020-11-13"));
        contract.setEndDate(Date.valueOf("2020-11-13"));
        contract.setContractAmount("3");
        contract.setContractQty("2");
        contract.setRegisterDate(LocalDateTime.now());
        contract.setRegisterUser("hs");
        contract.setUpdateDate(LocalDateTime.now());
        contract.setUpdateUser("hs");
        Contract newContract = contractRepository.save(contract);
        System.out.println("new Auth_User : " + newContract);
    }

    @Test
    public void read(){
        Optional<Contract> contract = contractRepository.findById(1L);
        contract.ifPresent(selectContract ->{
            System.out.println("contract:" + selectContract);
        });
    }

    @Test
    public void update(){
        Optional<Contract> contract = contractRepository.findById(1L);
        contract.ifPresent(selectContract -> {
            selectContract.setName("바꿨지롱");
            selectContract.setDepartmentIdx(88L);
            selectContract.setUserCode("test1");
            selectContract.setContractTypeIdx(22L);
            selectContract.setUpdateDate(LocalDateTime.now());
            selectContract.setUpdateUser("hs");
            contractRepository.save(selectContract);
        });
    }

    @Test
    public void delete(){
        Optional<Contract> contract = contractRepository.findById(1L);
        contract.ifPresent(selectContract ->{
            contractRepository.delete(selectContract);
        });
    }
}
