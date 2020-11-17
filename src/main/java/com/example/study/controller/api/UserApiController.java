package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserPasswordRequest;
import com.example.study.model.network.request.UserRequest;
import com.example.study.model.network.response.UserResponse;
import com.example.study.service.UserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserApiController implements CrudInterface<UserRequest, UserResponse> {

    @Autowired
    UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("/user")
    public Header<UserResponse> create(@RequestBody Header<UserRequest> request) {
        return userApiLogicService.create(request);
    }

    @Override
    @PutMapping("/user")
    public Header<UserResponse> update(@RequestBody Header<UserRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    public Header<UserResponse> read(Long id) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    @GetMapping("/user/{userCode}")
    public Header<UserResponse> readByUserCode(@PathVariable String userCode){
        return userApiLogicService.readByUserCode(userCode);
    }

    @PutMapping("/user/password")
    public Header updatePassword(@RequestBody Header<UserPasswordRequest> request){
        return userApiLogicService.updatePassword(request);
    }

    @DeleteMapping("/user/{userCode}")
    public Header deleteByUserCode(@PathVariable String userCode){
        return userApiLogicService.deleteByUserCode(userCode);
    }
}
