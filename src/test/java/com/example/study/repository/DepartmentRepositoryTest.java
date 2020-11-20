package com.example.study.repository;

<<<<<<< HEAD
=======
import com.example.study.model.entity.Department;
<<<<<<< HEAD
import com.example.study.model.entity.Team;
=======

import org.junit.Test;
>>>>>>> 1db4c611f3674d06ba7213925fd729f4fdf0275a
>>>>>>> e96389c62b0c40b596d565947d1e477cb833bf2e
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DepartmentRepositoryTest{

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    void saveDepartmentAndTeam(){
        //부서 생성
        Department department = new Department();
        department.setDepartment("부서1");

        departmentRepository.save(department);

        //팀 생성
        Team team1 = Team.builder().team("부서1-1팀").build();
        Team team2 = Team.builder().team("부서1-2팀").build();

        department.addTeam(team1);
        department.addTeam(team2);

        teamRepository.save(team1);
        teamRepository.save(team2);
    }

    @Test
    void readDepartmentAndTeam() {
        Department department = departmentRepository.findByIdx(27).orElseGet(null);

        System.out.println(department.getDepartment());
        System.out.println(department.getTeamList());
    }

    @Test
    void updateDepartmentAndTeam(){
        Department department = departmentRepository.findByIdx(27).orElseGet(null);

        department.setDepartment("부서1(변경4)");
        departmentRepository.save(department);

        Department updated = departmentRepository.findByIdx(27).orElseGet(null);
        Assertions.assertEquals(updated.getDepartment(), "부서1(변경4)");

        List<Team> teams = teamRepository.findAllByDepartmentIdx(27L);
        for(Team team : teams){
            Assertions.assertEquals(team.getDepartment(), "부서1(변경4)");
        }
    }

    @Test
    void deleteTeam(){
        Department department = departmentRepository.findByIdx(31).orElseGet(null);
        department.deleteTeamAll();
        departmentRepository.save(department);

        List<Team> teams = teamRepository.findAllByDepartmentIdx(31L);
        Assertions.assertEquals(teams.size(), 0);

    }
}
