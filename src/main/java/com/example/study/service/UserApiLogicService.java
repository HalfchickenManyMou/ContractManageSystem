package com.example.study.service;

import com.example.study.exception.commonException.DataNotFoundException;
import com.example.study.exception.userException.DuplicateEmailFoundException;
import com.example.study.exception.userException.DuplicateUserCodeException;
import com.example.study.exception.userException.InvalidPasswordException;
import com.example.study.exception.userException.UserNotFoundException;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserPasswordRequest;
import com.example.study.model.network.request.UserRequest;
import com.example.study.model.network.response.UserResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserRequest, UserResponse> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Header<UserResponse> create(Header<UserRequest> request) {
        UserRequest userRequest = request.getData();

        //사원번호 중복 체크? getOne을 써야하는지
        Optional<User> userCodeChk = userRepository.findByUserCode(userRequest.getUserCode());
        if(userCodeChk.isPresent()) throw new DuplicateUserCodeException();

        //Email 중복 체크? getOne을 써야하는지
        Optional<User> emailChk = userRepository.findByEmail(userRequest.getEmail());
        if(emailChk.isPresent()) throw new DuplicateEmailFoundException();

        User user = User.builder()
                .userCode(userRequest.getUserCode())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .pwd(passwordEncoder.encode(userRequest.getPwd()))
                .phoneNumber(userRequest.getPhoneNumber())
                .department(userRequest.getDepartment())
                .team(userRequest.getTeam())
                .rank(userRequest.getRank())
                .build();

        User inserted = userRepository.save(user);
        UserResponse res = response(inserted);

        return Header.OK(res);
    }

    @Override
    public Header<UserResponse> read(Long id){
        return null;
    }

    @Override
    public Header<UserResponse> update(Header<UserRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(body -> userRepository.findByUserCode(body.getUserCode())
                        .map(user -> {
                            user.setName(body.getName())
                                .setEmail(body.getEmail())
                                .setPhoneNumber(body.getPhoneNumber())
                                .setDepartment(body.getDepartment())
                                .setTeam(body.getTeam())
                                .setRank(body.getRank());
                            return userRepository.save(user);
                        })
                        .map(updated -> response(updated))
                        .map(Header::OK)
                .orElseThrow(UserNotFoundException::new)
                )
        .orElseThrow(DataNotFoundException::new);
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    public Header<UserResponse> readByUserCode(String userCode){
        return userRepository.findByUserCode(userCode)
                .map(user -> response(user))
                .map(Header::OK)
                .orElseThrow(DataNotFoundException::new);
    }

    public Header updatePassword(Header<UserPasswordRequest> request){
        return Optional.ofNullable(request.getData())
                .map(body -> userRepository.findByUserCode(body.getUserCode())
                    .map(user -> {
                        //비밀번호 확인, exception 발생
                        boolean chk = passwordEncoder.matches(body.getOriginPassword(), user.getPwd());
                        if(!chk) throw new InvalidPasswordException();

                        user.setPwd(passwordEncoder.encode(body.getNewPassword()));
                        userRepository.save(user);
                        return Header.OK();
                    })
                //findByUserCode 결과가 null일 경우
                .orElseThrow(UserNotFoundException::new)
                )
        .orElseThrow(DataNotFoundException::new);
    }

    public Header deleteByUserCode(String userCode) {
        return userRepository.findByUserCode(userCode)
                .map(user -> {
                    userRepository.deleteByUserCode(user.getUserCode());
                    return Header.OK();
                })
        .orElseThrow(DataNotFoundException::new);
    }
    private UserResponse response(User user) {
        UserResponse body = UserResponse.builder()
                .userCode(user.getUserCode())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .department(user.getDepartment())
                .team(user.getTeam())
                .rank(user.getRank())
                .registerDate(user.getRegisterDate())
                .registerUser(user.getRegisterUser())
                .updateDate(user.getUpdateDate())
                .updateUser(user.getUpdateUser())
                .build();
        return body;
    }
}