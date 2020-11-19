package com.example.study.service;


import com.example.study.config.ContractSpec;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Contract;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.ContractRequest;
import com.example.study.model.network.response.ContractResponse;
import com.example.study.repository.ContractRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractApiLogicService implements CrudInterface<ContractRequest, ContractResponse> {

    @Autowired
    ContractRepository ContractRepository;

    @Override
    public Header<ContractResponse> create(Header<ContractRequest> request) {
        return Optional.ofNullable(request.getData())
                .map( body ->{
                    LocalDate date = LocalDate.now();

                    Contract contract = Contract.builder()
                            .idx( body.getIdx() )
                            .name( body.getName() )
                            .code( body.getCode() )
                            .contractTypeIdx( body.getContractTypeIdx() )
                            .userCode( body.getUserCode() )
                            .departmentIdx( body.getDepartmentIdx() )
                            .content( body.getContent() )
                            .ownerName( body.getOwnerName() )
                            .ownerBusinessNumber( body.getOwnerBusinessNumber() )
                            .ownerAddress( body.getOwnerAddress() )
                            .otherName( body.getOtherName() )
                            .otherBusinessNumber( body.getOtherBusinessNumber() )
                            .otherAddress( body.getOtherAddress() )
                            .startDate( (body.getStartDate()==null)? date : LocalDate.parse( body.getStartDate(), DateTimeFormatter.ISO_DATE ) )
                            .endDate( (body.getEndDate()==null)? date : LocalDate.parse( body.getEndDate(), DateTimeFormatter.ISO_DATE ) )
                            .contractAmount( body.getContractAmount() )
                            .contractQty( body.getContractQty() )
                            .registerDate( (body.getRegisterDate()==null)? date : LocalDate.parse( body.getEndDate(), DateTimeFormatter.ISO_DATE ) )
                            .registerUser( body.getRegisterUser() )
                            .updateDate( (body.getUpdateDate()==null)? date : LocalDate.parse( body.getEndDate(), DateTimeFormatter.ISO_DATE ))
                            .updateUser( body.getUpdateUser() )
                            .build();

                    return contract;
                })
                .map(saved -> ContractRepository.save(saved))
                .map(saved -> response(saved))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ContractResponse> read(Long idx) {
        return ContractRepository.findById( idx )
                .map(r -> response(r))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    @Transactional
    public Header delete(Long idx) {
        return ContractRepository.findById(idx)
                .map(deleted -> {
                    ContractRepository.deleteById(idx);
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public Header<List<ContractResponse>> readAll( Pageable pageable, ContractRequest request ) {

        Page<Contract> contracts = ContractRepository.findAll(
                ContractSpec.idx( request.getIdx() ).and(
                ContractSpec.contractName(request.getName())).and(
                ContractSpec.register_user(request.getRegisterUser())).and(
                ContractSpec.owner_name(request.getOwnerName())).and(
                ContractSpec.owner_business_number( request.getOwnerBusinessNumber())).and(
                ContractSpec.owner_address( request.getOwnerAddress())).and(
                ContractSpec.ohter_name( request.getOtherName() )).and(
                ContractSpec.other_business_number( request.getOtherBusinessNumber())).and(
                ContractSpec.other_address( request.getOtherAddress())).and(
                ContractSpec.start_date( request.getStartDate())).and(
                ContractSpec.end_date( request.getEndDate()))

                , pageable );

        List<ContractResponse> responsesList = contracts.stream()
                .map(r -> response(r))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(contracts.getTotalPages())
                .totalElements(contracts.getTotalElements())
                .currentPage(contracts.getNumber())
                .currentElements(contracts.getNumberOfElements())
                .build();

        return Header.OK( responsesList, pagination );
    }

    @Override
    public Header<ContractResponse> update(Header<ContractRequest> request) {

        System.out.println(" update request date : "+ request.getData());
        return Optional.ofNullable(request.getData())
                .map(body -> {
                    return ContractRepository.findById( body.getIdx() )
                            .map(
                                updated -> {
                                    LocalDate date = LocalDate.now();
                                    return updated
                                            .setIdx( body.getIdx() )
                                            .setCode( body.getCode() )
                                            .setName( body.getName() )
                                            .setContractTypeIdx( body.getContractTypeIdx() )
                                            .setUserCode( body.getUserCode() )
                                            .setDepartmentIdx( body.getDepartmentIdx() )
                                            .setContent( body.getContent() )
                                            .setOwnerName( body.getOwnerName() )
                                            .setOwnerBusinessNumber( body.getOwnerBusinessNumber() )
                                            .setOwnerAddress( body.getOwnerAddress() )
                                            .setOtherName( body.getOtherName() )
                                            .setOtherBusinessNumber( body.getOtherBusinessNumber() )
                                            .setOtherAddress( body.getOtherAddress() )
                                            .setStartDate( (body.getStartDate()==null)? null : LocalDate.parse( body.getStartDate(), DateTimeFormatter.ISO_DATE ) )
                                            .setEndDate( (body.getEndDate()==null)? null : LocalDate.parse( body.getEndDate(), DateTimeFormatter.ISO_DATE ) )
                                            .setContractAmount( body.getContractAmount() )
                                            .setContractQty( body.getContractQty() )
                                            .setUpdateDate( (body.getUpdateDate()==null)? date : LocalDate.parse( body.getEndDate(), DateTimeFormatter.ISO_DATE ))
                                            .setUpdateUser( body.getUpdateUser() );
                                }
                            )
                            .map(updatedType -> ContractRepository.save(updatedType))
                            .map(saved -> response(saved))
                            .map(Header::OK)
                            .orElseGet(()->Header.ERROR("데이터 없음"));
                }).orElseGet(()->Header.ERROR("데이터 없음"));
    }


    public Header<List<Contract>> bulkCreate(Header<List<Contract>> request) {
        return Optional.ofNullable(request.getData())
                .map(typeList -> ContractRepository.saveAll(typeList) )
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    //응답 포맷
    public ContractResponse response(Contract contract){
        return ContractResponse.builder()
                .idx( contract.getIdx() )
                .code( contract.getCode() )
                .name( contract.getName() )
                .contractTypeIdx( contract.getContractTypeIdx() )
                .userCode( contract.getUserCode() )
                .departmentIdx( contract.getDepartmentIdx() )
                .content( contract.getContent() )
                .ownerName( contract.getOwnerName() )
                .ownerBusinessNumber( contract.getOwnerBusinessNumber() )
                .ownerAddress( contract.getOwnerAddress() )
                .otherName( contract.getOtherName() )
                .otherBusinessNumber( contract.getOtherBusinessNumber() )
                .otherAddress( contract.getOtherAddress() )
                .startDate( contract.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) )
                .endDate( contract.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) )
                .contractAmount( contract.getContractAmount() )
                .contractQty( contract.getContractQty() )
                .registerDate( contract.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) )
                .registerUser( contract.getRegisterUser() )
                .updateDate( contract.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) )
                .updateUser( contract.getUpdateUser() )
                .build();

    }
}
