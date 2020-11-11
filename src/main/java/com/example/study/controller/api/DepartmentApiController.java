package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.DepartmentRequest;
import com.example.study.model.network.response.DepartmentResponse;
import com.example.study.service.DepartmentApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class DepartmentApiController implements CrudInterface<DepartmentRequest, DepartmentResponse> {

    @Autowired
    DepartmentApiLogicService departmentApiLogicService;

    @Override
    @PostMapping("/department")
    public Header<DepartmentResponse> create(@RequestBody Header<DepartmentRequest> request) {
        System.out.println(request);
        return departmentApiLogicService.create(request);
    }

    @Override
    @GetMapping("/department/{idx}")
    public Header<DepartmentResponse> read(@PathVariable Long idx) {
        return departmentApiLogicService.read(idx);
    }

    @GetMapping("/department")
    public Header<List<DepartmentResponse>> readAll() {
        return departmentApiLogicService.readAll();
    }

    @Override
    @PutMapping("/department")
    public Header<DepartmentResponse> update(@RequestBody Header<DepartmentRequest> request) {
        return departmentApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("/department/{idx}")
    public Header delete(@PathVariable Long idx) {
        return departmentApiLogicService.delete(idx);
    }


}
