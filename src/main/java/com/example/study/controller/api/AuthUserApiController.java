package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AuthUserRequest;
import com.example.study.model.network.response.AuthUserResponse;
import com.example.study.service.AuthUserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class AuthUserApiController implements CrudInterface<AuthUserRequest, AuthUserResponse> {

    @Autowired
    AuthUserApiLogicService authuserApiLogicService;

    @PostMapping("/authInquire")
    public Header<AuthUserResponse> readInquire(@PathVariable Long idx){
        return authuserApiLogicService.read(idx);
    }

    @Override
    @PostMapping("/authuser")
    public Header<AuthUserResponse> create(@RequestBody Header<AuthUserRequest> request) {
        System.out.println(request);
        return authuserApiLogicService.create(request);
    }

    @Override
    @GetMapping("/authuser/{idx}")
    public Header<AuthUserResponse> read(@PathVariable Long idx) {

        return authuserApiLogicService.read(idx);
    }

    @GetMapping("/authuser")
    public Header<List<AuthUserResponse>> readAll() {
        return authuserApiLogicService.readAll();
    }

    @Override
    @PutMapping("/authuser")
    public Header<AuthUserResponse> update(@RequestBody Header<AuthUserRequest> request) {
        return authuserApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("/authuser/{idx}")
    public Header delete(@PathVariable Long idx) {
        return authuserApiLogicService.delete(idx);
    }


}
