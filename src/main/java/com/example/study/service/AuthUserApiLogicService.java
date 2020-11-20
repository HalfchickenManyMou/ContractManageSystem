package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.AuthUser;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AuthUserRequest;
import com.example.study.model.network.response.AuthUserResponse;
import com.example.study.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AuthUserApiLogicService implements CrudInterface<AuthUserRequest, AuthUserResponse> {

    @Autowired
    AuthUserRepository authuserRepository;

    @Override
    public Header<AuthUserResponse> create(Header<AuthUserRequest> request){
        return Optional.ofNullable(request.getData())
                .map(au -> {
                    AuthUser authuser = AuthUser.builder()
                            .useridx(au.getUseridx())
                            .build();
                    return authuser;
                })
                .map(au -> authuserRepository.save(au))
                .map(saved -> response(saved))
                .map(Header::OK)
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AuthUserResponse> read(Long idx) {
        return authuserRepository.findByIdx(idx)
                .map(au -> response(au))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public Header<List<AuthUserResponse>> readAll() {
        List<AuthUser> authusers = authuserRepository.findAll();
        List<AuthUserResponse> responsesList = authusers.stream()
                .map(au -> response(au))
                .collect(Collectors.toList());

        return Header.OK(responsesList);
    }


    @Override
    public Header<AuthUserResponse> update(Header<AuthUserRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(body -> authuserRepository.findByIdx(body.getIdx())
                        .map(au -> {
                            au.setUseridx(body.getUseridx());
                            return au;
                        })
                        .map(au -> authuserRepository.save(au))
                        .map(updated -> response(updated))
                        .map(Header::OK)
                        .orElseGet( ()-> Header.ERROR("데이터 없음")))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    @Transactional
    public Header delete(Long idx) {
        return authuserRepository.findByIdx(idx)
                .map(deleted -> {
                    authuserRepository.deleteByIdx(idx);
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }


    public AuthUserResponse response(AuthUser authuser){
        AuthUserResponse body = AuthUserResponse.builder()
                .idx(authuser.getIdx())
                .contract_idx(authuser.getContract_idx())
                .useridx(authuser.getUseridx())
                .team_idx(authuser.getTeam_idx())
                .department_idx(authuser.getDepartment_idx())
                .auth_type(authuser.getAuth_type())
                .build();
        return body;
    }
}
