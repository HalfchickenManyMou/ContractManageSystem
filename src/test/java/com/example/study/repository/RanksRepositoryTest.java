package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Ranks;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


class RanksRepositoryTest extends StudyApplicationTests{
    @Autowired
    RankRepository rankRepository;

    @Test
    void create(){
        Ranks ranks = Ranks.builder()
                .rankName("사원")
                .build();
        Ranks saved = rankRepository.save(ranks);
        Assertions.assertNotNull(saved);
    }

    @Test
    void read(){
        List<Ranks> ranks = rankRepository.findAll();
        ranks.stream().forEach(r->{
            System.out.println(r);
        });
    }

    @Test
    void update(){
        Optional<Ranks> rank = rankRepository.findByIdx(1L);
        rank.ifPresent(r->{
            r.setRankName("대리");
            rankRepository.save(r);
        });
    }

    @Test
    @Transactional
    void delete(){
        Optional<Ranks> rank = rankRepository.findByIdx(1L);
        Assertions.assertTrue(rank.isPresent());

        rank.ifPresent(r->{
            rankRepository.delete(r);
        });

        Optional<Ranks> deleted = rankRepository.findByIdx(1L);
        Assertions.assertFalse(deleted.isPresent());
    }
}