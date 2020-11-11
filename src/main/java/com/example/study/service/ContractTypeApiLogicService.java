package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.ContractType;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ContractTypeRequest;
import com.example.study.model.network.response.ContractTypeResponse;
import com.example.study.repository.ContractTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractTypeApiLogicService implements CrudInterface<ContractTypeRequest, ContractTypeResponse> {

    @Autowired
    ContractTypeRepository contractTypeRepository;

    @Override
    public Header<ContractTypeResponse> create(Header<ContractTypeRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(body ->{
                    ContractType contractType =
                            ContractType.builder()
                            .type(body.getType())
                            .build();
                    return contractType;
                })
                .map(saved -> contractTypeRepository.save(saved))
                .map(saved -> response(saved))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ContractTypeResponse> read(Long idx) {
        return contractTypeRepository.findByIdx(idx)
                .map(r -> response(r))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public Header<List<ContractTypeResponse>> readAll() {
        List<ContractType> contractTypes = contractTypeRepository.findAll();
        List<ContractTypeResponse> responsesList = contractTypes.stream()
                .map(r -> response(r))
                .collect(Collectors.toList());

        return Header.OK(responsesList);
    }

    @Override
    public Header<ContractTypeResponse> update(Header<ContractTypeRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(body -> {
                    return contractTypeRepository.findByIdx(body.getIdx())
                            .map(updated -> {
                                updated.setType(body.getType());
                                return updated;
                            })
                            .map(updatedType -> contractTypeRepository.save(updatedType))
                            .map(saved -> response(saved))
                            .map(Header::OK)
                            .orElseGet(()->Header.ERROR("데이터 없음"));
                }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    @Transactional
    public Header delete(Long idx) {
        return contractTypeRepository.findByIdx(idx)
                .map(deleted -> {
                    contractTypeRepository.deleteByIdx(idx);
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Transactional
    public Header<List<ContractType>> allDeleteAndCreate(Header<List<ContractType>> request) {
        contractTypeRepository.deleteAll();
        return Optional.ofNullable(request.getData())
                .map(typeList -> contractTypeRepository.saveAll(typeList))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    //응답 포맷
    public ContractTypeResponse response(ContractType contractType){
        //TODO  REGISTER_USER, UPDATE_USER 추가
        ContractTypeResponse body = ContractTypeResponse.builder()
                .idx(contractType.getIdx())
                .type(contractType.getType())
                .registerDate(contractType.getRegisterDate())
                .updateDate(contractType.getUpdateDate())
                .build();
        return body;
    }
}
