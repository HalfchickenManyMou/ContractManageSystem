package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Department;
import com.example.study.model.entity.Team;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.DepartmentRequest;
import com.example.study.model.network.response.DepartmentResponse;
import com.example.study.repository.DepartmentRepository;
import com.example.study.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentApiLogicService implements CrudInterface<DepartmentRequest, DepartmentResponse> {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Header<DepartmentResponse> create(Header<DepartmentRequest> request){
        return Optional.ofNullable(request.getData())
                .map(dpt -> {
                    Department department = Department.builder()
                            .department(dpt.getDepartment())
                            .build();
                    return department;
                })
                .map(dpt -> departmentRepository.save(dpt))
                .map(saved -> response(saved))
                .map(Header::OK)
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<DepartmentResponse> read(Long idx) {
        return departmentRepository.findByIdx(idx)
                .map(dpt -> response(dpt))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public Header<List<DepartmentResponse>> readAll() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentResponse> responsesList = departments.stream()
                .map(dpt -> response(dpt))
                .collect(Collectors.toList());
        return Header.OK(responsesList);
    }


    @Override
    public Header<DepartmentResponse> update(Header<DepartmentRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(body -> departmentRepository.findByIdx(body.getIdx())
                        .map(dpt -> {
                            dpt.setDepartment(body.getDepartment());
                            return dpt;
                        })
                        .map(dpt -> departmentRepository.save(dpt))
                        .map(updated -> response(updated))
                        .map(Header::OK)
                        .orElseGet( ()-> Header.ERROR("데이터 없음")))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    @Transactional
    public Header delete(Long idx) {
        return departmentRepository.findByIdx(idx)
                .map(deleted -> {
                    departmentRepository.deleteByIdx(idx);
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public Header bulkCreate(Header<DepartmentRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(body -> {
                    Department department = new Department();
                    department.setDepartment(body.getDepartment());
                    departmentRepository.save(department);

                    for(Team team : body.getTeamList()){
                        department.addTeam(team);
                        teamRepository.save(team);
                    }
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header bulkUpdate(Header<DepartmentRequest> request, Long idx) {
        return Optional.ofNullable(request.getData())
                .map(body -> departmentRepository.findByIdx(idx)
                    .map(dpt -> {
                        dpt.setDepartment(body.getDepartment());
                        dpt.deleteTeamAll();
                        departmentRepository.save(dpt);

                        for(Team team : body.getTeamList()){
                            dpt.addTeam(team);
                            teamRepository.save(team);
                        }
                        return Header.OK();
                    })
                    .orElseGet(()->Header.ERROR("데이터 없음"))
                )
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public DepartmentResponse response(Department department){
        DepartmentResponse body = DepartmentResponse.builder()
                .idx(department.getIdx())
                .department(department.getDepartment())
                .teamList(department.getTeamList())
                .build();
        return body;
    }

    public Header readList( ) {
        List<Department> departments = departmentRepository.findAll();
        List<List> responsesList = departments.stream()
                .map(dpt -> new ArrayList( Arrays.asList( dpt.getIdx(), dpt.getDepartment() ) ) )
                .collect(Collectors.toList());
        return Header.OK(responsesList);
    }

}
