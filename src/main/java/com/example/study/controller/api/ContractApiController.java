package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ContractRequest;
import com.example.study.model.network.response.ContractResponse;
import com.example.study.service.ContractApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContractApiController implements CrudInterface<ContractRequest, ContractResponse> {

    @Autowired
    ContractApiLogicService contractApiLogicService;

    @Override
    @PostMapping("/contract")
    public Header<ContractResponse> create(@RequestBody Header<ContractRequest> request ) {
        System.out.println("contract post request : "+request);
        return contractApiLogicService.create(request);
    }

    @Override
    @GetMapping("/contract/{idx}")
    public Header<ContractResponse> read(@PathVariable Long idx) {
        return contractApiLogicService.read(idx);
    }

    @Override
    @PutMapping("/contract")
    public Header<ContractResponse> update(@RequestBody Header<ContractRequest> request) {
        return contractApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("/contract/{idx}")
    public Header delete(@PathVariable Long idx) {
        return contractApiLogicService.delete(idx);
    }

    @GetMapping("/contract")
    @ResponseBody
    public Header<List<ContractResponse>> readAll(@PageableDefault(sort = { "idx" }, direction = Sort.Direction.ASC) Pageable pageable, ContractRequest request ) {
        return contractApiLogicService.readAll( pageable , request );
    }


}
