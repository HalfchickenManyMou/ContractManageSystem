package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Contract;
import com.example.study.model.entity.Ranks;
import com.example.study.model.entity.Team;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.TeamRequest;
import com.example.study.model.network.response.ContractResponse;
import com.example.study.model.network.response.TeamResponse;
import com.example.study.repository.DepartmentRepository;
import com.example.study.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamApiLogicService implements CrudInterface<TeamRequest, TeamResponse> {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Header<TeamResponse> create(Header<TeamRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(req -> {
                    Team team = Team.builder()
                            .team(req.getTeam())
                            .department(departmentRepository.getOne(req.getDepartmentIdx()))
                            .build();
                    return team;
                })
                .map(team -> teamRepository.save(team))
                .map(saved -> response(saved))
                .map(Header::OK)
                .orElseGet( () -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<TeamResponse> read(Long idx) {
        return teamRepository.findByIdx(idx)
                .map(team -> response(team))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header<List<TeamResponse>> readByDepartmentIdx(Long departmentIdx) {
        List<Team> teams = teamRepository.findAllByDepartmentIdx(departmentIdx);
        //System.out.println(teams);
        List<TeamResponse> teamList = teams.stream()
                .map(team -> response(team))
                .collect(Collectors.toList());

        return Header.OK(teamList);
    }

    @Override
    public Header<TeamResponse> update(Header<TeamRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(body -> teamRepository.findByIdx(body.getIdx())
                .map(updated -> {
                    updated.setTeam(body.getTeam())
                           .setDepartment(departmentRepository.getOne(body.getDepartmentIdx()));
                    return updated;
                })
                .map(updated -> teamRepository.save(updated))
                .map(updated -> response(updated))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"))
                )
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    @Transactional
    public Header delete(Long idx) {
        return teamRepository.findByIdx(idx)
                .map(team -> {
                    teamRepository.delete(team);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public TeamResponse response(Team team){
       TeamResponse body = TeamResponse.builder()
               .idx(team.getIdx())
               .departmentIdx(team.getDepartment().getIdx())
               .team(team.getTeam())
               .build();
        return body;
    }
	public Header<List<TeamResponse>> readAll() {
        List<Team> contracts = teamRepository.findAll();
        List<TeamResponse> responsesList = contracts.stream()
                .map(r -> response(r))
                .collect(Collectors.toList());

        return Header.OK(responsesList);
    }
    public Header readList( ) {
        List<Team> departments = teamRepository.findAll();
        List<List> responsesList = departments.stream()
                .map(team -> new ArrayList( Arrays.asList( team.getIdx(), team.getTeam() ) ) )
                .collect(Collectors.toList());
        return Header.OK(responsesList);
    }

}
