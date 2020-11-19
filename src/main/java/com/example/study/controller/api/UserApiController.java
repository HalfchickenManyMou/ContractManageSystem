package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserPasswordRequest;
import com.example.study.model.network.request.UserRequest;
import com.example.study.model.network.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.example.study.service.UserApiLogicService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApiController implements CrudInterface<UserRequest, UserResponse> {

    @Autowired
    UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("/user")
    public Header<UserResponse> create(@RequestBody Header<UserRequest> request) {
        System.out.println("user create request : "+request);
        return userApiLogicService.create(request);
    }

    @Override
    @PutMapping("/user")
    public Header<UserResponse> update(@RequestBody Header<UserRequest> request) {

        System.out.println("user update request : "+request);
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


    @GetMapping("/users")
    @ResponseBody
    public Header< List<UserResponse> > readAll(@PageableDefault(sort = { "userCode" }, direction = Sort.Direction.ASC) Pageable pageable, UserRequest request ) {

        System.out.println("request : "+request);
        return userApiLogicService.readAll( pageable, request );
    }

}
