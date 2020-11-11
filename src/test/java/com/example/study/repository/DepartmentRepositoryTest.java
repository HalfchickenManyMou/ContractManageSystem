package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Department;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

class DepartmentRepositoryTest extends StudyApplicationTests {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void create(){
        Department department = new Department()
                .setDepartment("영업부");
        Department newDepartment = departmentRepository.save(department);
        Assertions.assertNotNull(newDepartment);
    }

    @Test
    @Transactional
    void read(){
        Optional<Department> department = departmentRepository.findByIdx(3L);
        department.ifPresent(d-> {
            System.out.println("--------부서--------");
            System.out.println(d.getDepartment());
            System.out.println("--------팀--------");
            d.getTeamList().stream().forEach(t->{
                System.out.println(t.getTeam());
            });
        });
    }

    @Test
    @Transactional
    void update(){
        Optional<Department> department = departmentRepository.findByIdx(3L);
        department.ifPresent(d->{
            d.setDepartment("영업부");
            departmentRepository.save(d);
        });
    }

    @Test
    @Transactional
    void delete(){
        Optional<Department> department = departmentRepository.findByIdx(3L);
        Assertions.assertTrue(department.isPresent());

        department.ifPresent(d->{
            departmentRepository.delete(d);
        });

        Optional<Department> deleted = departmentRepository.findByIdx(3L);
        Assertions.assertFalse(deleted.isPresent());
    }
}