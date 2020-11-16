//package com.example.study.controller.api;
//
//import com.example.study.ifs.CrudInterface;
//import com.example.study.model.network.Header;
//import com.example.study.model.network.request.UserApiRequest;
//import com.example.study.model.network.response.UserApiResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/api")
//public class UserApiController implements CrudInterface<UserApiRequest,UserApiResponse> {
//
//    @Autowired
//    UserInfoService userInfoService;
//
//    @Override
//    @PostMapping("/myInfo")
//    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
//        return userInfoService.create(request);
//    }
//
//    @Override
//    @GetMapping("/myInfo/{id}")
//    public Header<UserApiResponse> read(@PathVariable  Long id) {
//        return userInfoService.read(id);
//    }
//
//    @GetMapping("/myInfo")
//    public Header<List<UserApiResponse>> readAll(){
//        return userInfoService.readAll();
//    }
//
//
//    @Override
//    @PutMapping("/myInfo")
//    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
//
//        return userInfoService.update(request);
//    }
//
//    @Override
//    @PutMapping("/myInfo/{id}")
//    public Header delete(Long id) {
//
//        return userInfoService.delete(id);
//    }
//}
