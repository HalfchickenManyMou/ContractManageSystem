package com.example.study.service;

import com.example.study.config.ContractSpec;
import com.example.study.config.UserSpec;
import com.example.study.exception.commonException.DataNotFoundException;
import com.example.study.exception.userException.DuplicateEmailFoundException;
import com.example.study.exception.userException.DuplicateUserCodeException;
import com.example.study.exception.userException.InvalidPasswordException;
import com.example.study.exception.userException.UserNotFoundException;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Contract;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.UserPasswordRequest;
import com.example.study.model.network.request.UserRequest;
import com.example.study.model.network.response.ContractResponse;
import com.example.study.model.network.response.UserResponse;
import com.example.study.repository.UserRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService implements CrudInterface<UserRequest, UserResponse> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Header<UserResponse> create(Header<UserRequest> request) {
        UserRequest userRequest = request.getData();
        /*
        //사원번호 중복 체크? getOne을 써야하는지
        Optional<User> userCodeChk = userRepository.findByUserCode(userRequest.getUserCode());
        if(userCodeChk.isPresent()) throw new DuplicateUserCodeException();

        //Email 중복 체크? getOne을 써야하는지
        Optional<User> emailChk = userRepository.findByEmail(userRequest.getEmail());
        if(emailChk.isPresent()) throw new DuplicateEmailFoundException();
*/
        User user = User.builder()
                .userCode(userRequest.getUserCode())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .pwd(passwordEncoder.encode(userRequest.getEmail()))
                .phoneNumber(userRequest.getPhoneNumber())
                .department_idx(userRequest.getDepartmentIdx())
                .team_idx(userRequest.getTeamIdx())
                .rank_idx(userRequest.getRankIdx())
                .role(userRequest.getRole())
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
                                .setDepartment_idx(body.getDepartmentIdx())
                                .setTeam_idx(body.getTeamIdx())
                                .setRank_idx(body.getRankIdx());
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
        //LocalDateTime 형식 바꾸기
        String registerDate = user.getRegisterDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String updateDate = user.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        UserResponse body = UserResponse.builder()
                .userCode(user.getUserCode())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .department(user.getDepartment())
                .team(user.getTeam())
                .rank(user.getRank())
                .registerDate(registerDate)
                .registerUser(user.getRegisterUser())
                .updateDate(updateDate)
                .updateUser(user.getUpdateUser())
                .build();
        return body;
    }

    public Header< List<UserResponse> > readAll(Pageable pageable, UserRequest request){

        Page<User> users = userRepository.findAll(
                UserSpec.userCode( request.getUserCode() ).and(
                UserSpec.userName(request.getName())).and(
                UserSpec.department_idx(request.getDepartmentIdx())).and(
                UserSpec.team_idx(request.getTeamIdx())).and(
                UserSpec.rank_idx( request.getRankIdx()))
                , pageable );

        List<UserResponse> responsesList = users.stream()
                .map(r -> response(r))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        return Header.OK( responsesList, pagination );
    }
}
