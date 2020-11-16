package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ContractRequest;
import com.example.study.model.network.response.ContractResponse;
import com.example.study.service.ContractApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContractApiController implements CrudInterface<ContractRequest, ContractResponse> {

    @Autowired
    ContractApiLogicService contractApiLogicService;

    @Override
    @PostMapping("/contract")
    public Header<ContractResponse> create(@RequestBody Header<ContractRequest> request) {
        return contractApiLogicService.create(request);
    }

    @Override
    @GetMapping("/contract/{id}")
    public Header<ContractResponse> read(@PathVariable  Long id) {

        return contractApiLogicService.read(id);
    }

    @GetMapping("/contract")
    public Header<List<ContractResponse>> readAll(){
        return contractApiLogicService.readAll();
    }


    @Override
    @PutMapping("/contract")
    public Header<ContractResponse> update(Header<ContractRequest> request) {

        return contractApiLogicService.update(request);
    }

    @Override
    @PutMapping("/contract/{id}")
    public Header delete(Long id) {

        return contractApiLogicService.delete(id);
    }
}
