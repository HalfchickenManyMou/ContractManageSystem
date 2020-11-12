package com.example.study.repository;


import com.example.study.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    void deleteByIdx(Long idx);
    void deleteAllByDepartmentIdx(int departmentIdx);
    List<Team> findAllByDepartmentIdx(Long departmentIdx);
    List<Team> findAll();
    Team save(Team team);
    Optional<Team> findByIdx(Long idx);
}
