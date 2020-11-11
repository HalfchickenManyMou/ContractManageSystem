package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.ContractType;
import com.example.study.model.entity.Ranks;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ContractTypeRequest;
import com.example.study.model.network.response.ContractTypeResponse;
import com.example.study.model.network.response.DepartmentResponse;
import com.example.study.service.ContractTypeApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContractTypeApiController implements CrudInterface<ContractTypeRequest, ContractTypeResponse> {

    @Autowired
    ContractTypeApiLogicService contractTypeApiLogicService;

    @Override
    @PostMapping("/contractType")
    public Header<ContractTypeResponse> create(@RequestBody Header<ContractTypeRequest> request) {
        return contractTypeApiLogicService.create(request);
    }

    @Override
    @GetMapping("/contractType/{idx}")
    public Header<ContractTypeResponse> read(@PathVariable Long idx) {
        return contractTypeApiLogicService.read(idx);
    }

    @Override
    @PutMapping("/contractType")
    public Header<ContractTypeResponse> update(@RequestBody Header<ContractTypeRequest> request) {
        return contractTypeApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("/contractType/{idx}")
    public Header delete(@PathVariable Long idx) {
        return contractTypeApiLogicService.delete(idx);
    }

    @PostMapping("/contractType/all")
    public Header<List<ContractType>> allDeleteAndCreate(@RequestBody Header<List<ContractType>> request) {
        return contractTypeApiLogicService.allDeleteAndCreate(request);
    }

    @GetMapping("/contractType")
    public Header<List<ContractTypeResponse>> readAll() {
        return contractTypeApiLogicService.readAll();
    }
}
