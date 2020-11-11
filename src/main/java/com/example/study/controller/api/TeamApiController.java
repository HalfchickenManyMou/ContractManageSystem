package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.TeamRequest;
import com.example.study.model.network.response.TeamResponse;
import com.example.study.service.TeamApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamApiController implements CrudInterface<TeamRequest, TeamResponse> {

    @Autowired
    TeamApiLogicService teamApiLogicService;

    @Override
    @PostMapping("/team")
    public Header<TeamResponse> create(@RequestBody Header<TeamRequest> request) {
        System.out.println(request);
        return teamApiLogicService.create(request);
    }

    @Override
    @GetMapping("/team/{idx}")
    public Header<TeamResponse> read(@PathVariable Long idx) {
        return teamApiLogicService.read(idx);
    }

    @GetMapping("/team/department/{departmentIdx}")
    public Header<List<TeamResponse>> readByDepartmentIdx(@PathVariable Long departmentIdx) {
        return teamApiLogicService.readByDepartmentIdx(departmentIdx);
    }

    @Override
    @PutMapping("/team")
    public Header<TeamResponse> update(@RequestBody Header<TeamRequest> request) {
        return teamApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("/team/{idx}")
    public Header delete(@PathVariable Long idx) {
        return teamApiLogicService.delete(idx);
    }
}
