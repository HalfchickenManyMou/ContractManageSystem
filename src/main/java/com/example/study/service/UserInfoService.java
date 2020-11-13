package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.AuthUser;
import com.example.study.model.entity.Department;
import com.example.study.model.entity.Ranks;
import com.example.study.model.entity.User;
import com.example.study.model.front.AdminMenu;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AuthUserRequest;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.AuthUserResponse;
import com.example.study.model.network.response.DepartmentResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    UserRepository userRepository;


    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(u -> {
                    User user = User.builder()
                            .userCode(u.getUserCode())
                            .build();
                    return user;
                })
                .map(u -> userRepository.save(u))
                .map(saved -> response(saved))
                .map(Header::OK)
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    private UserApiResponse response(User user) {
        UserApiResponse body = UserApiResponse.builder()
                .userCode(user.getUserCode())
//                TODO : stackOverFlow 에러 해결 필요
//                .teamList(department.getTeamList())
                .build();
        return body;
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        return userRepository.findById(String.valueOf(id))
                .map(u -> response(u))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }


    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        return Optional.ofNullable(request.getData())
                .map(body -> userRepository.findById(body.getUserCode())
                        .map(au -> {
                            au.setUserCode(body.getUserCode());
                            return au;
                        })
                        .map(au -> userRepository.save(au))
                        .map(updated -> response(updated))
                        .map(Header::OK)
                        .orElseGet( ()-> Header.ERROR("데이터 없음")))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return userRepository.findById(String.valueOf(id))
                .map(deleted -> {
                    userRepository.deleteById(String.valueOf(id));
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public Header<List<UserApiResponse>> readAll() {
        List<User> users = userRepository.findAll();
        List<UserApiResponse> responsesList = users.stream()
                .map(u -> response(u))
                .collect(Collectors.toList());

        return Header.OK(responsesList);
    }
}
