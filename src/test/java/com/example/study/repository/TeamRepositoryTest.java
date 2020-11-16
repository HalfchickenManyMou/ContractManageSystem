package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Department;
import com.example.study.model.entity.Team;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

class TeamRepositoryTest extends StudyApplicationTests {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void create(){
        Optional<Department> department = departmentRepository.findByIdx(1L);
        department.ifPresent(d->{
            Team team = Team.builder()
                    .team("1팀")
                    .department(d)
                    .build();
            Team saved = teamRepository.save(team);
            Assertions.assertNotNull(saved);
        });
    }

    @Test
    void read(){
        List<Team> teams = teamRepository.findAll();
        teams.stream().forEach(t->{
            System.out.println("--------------");
            System.out.println(t.getDepartment().getDepartment());
            System.out.println(t.getDepartment().getIdx());
            System.out.println(t.getTeam());
            System.out.println("--------------");
        });
    }
}