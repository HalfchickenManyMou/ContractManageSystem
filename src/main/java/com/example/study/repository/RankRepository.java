package com.example.study.repository;


import com.example.study.model.entity.Ranks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankRepository extends JpaRepository<Ranks, Long> {
    Optional<Ranks> findByIdx(long idx);
    void deleteByIdx(Long idx);
}
