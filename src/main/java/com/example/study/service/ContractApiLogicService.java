package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Contract;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ContractRequest;
import com.example.study.model.network.response.ContractResponse;
import com.example.study.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractApiLogicService implements CrudInterface<ContractRequest, ContractResponse> {
    @Autowired
    ContractRepository contractRepository;

    @Override
    public Header<ContractResponse> create(Header<ContractRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(u -> {
                    Contract contract = Contract.builder()
                            .userCode(u.getUserCode())
                            .build();
                    return contract;
                })
                .map(u -> contractRepository.save(u))
                .map(saved -> response(saved))
                .map(Header::OK)
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    private ContractResponse response(Contract contract) {
        ContractResponse body = ContractResponse.builder()
                .idx(contract.getIdx())
                .contractTypeIdx(contract.getContractTypeIdx())
//                TODO : stackOverFlow 에러 해결 필요
//                .teamList(department.getTeamList())
                .build();
        return body;
    }

    @Override
    public Header<ContractResponse> read(Long id) {
        return contractRepository.findById(id)
                .map(ct -> response(ct))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ContractResponse> update(Header<ContractRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(body -> {
                    return contractRepository.findById(body.getIdx())
                            .map(updated -> {
                                updated.setCode(body.getCode());
                                return updated;
                            })
                            .map(updatedCode -> contractRepository.save(updatedCode))
                            .map(saved -> response(saved))
                            .map(Header::OK)
                            .orElseGet(()->Header.ERROR("데이터 없음"));
                }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return contractRepository.findById(id)
                .map(deleted -> {
                    contractRepository.deleteById(id);
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public Header<List<ContractResponse>> readAll() {
        List<Contract> contracts = contractRepository.findAll();
        List<ContractResponse> responsesList = contracts.stream()
                .map(r -> response(r))
                .collect(Collectors.toList());

        return Header.OK(responsesList);
    }
}
