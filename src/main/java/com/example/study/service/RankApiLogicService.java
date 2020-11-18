package com.example.study.service;

import com.example.study.model.entity.Ranks;
import com.example.study.model.network.Header;
import com.example.study.model.network.response.RankResponse;
import com.example.study.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RankApiLogicService{

    @Autowired
    RankRepository rankRepository;

    public Header<List<Ranks>> bulkCreate(Header<List<Ranks>> request) {
        return Optional.ofNullable(request.getData())
                .map(rankList -> rankRepository.saveAll(rankList))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header<List<RankResponse>> readAll() {
        List<Ranks> ranks = rankRepository.findAll();
        List<RankResponse> responsesList = ranks.stream()
                .map(rank -> response(rank))
                .collect(Collectors.toList());

        return Header.OK(responsesList);
    }

    @Transactional
    public Header delete(Long idx) {
        return rankRepository.findByIdx(idx)
                .map(deleted -> {
                    rankRepository.deleteByIdx(idx);
                    return Header.OK();
                })
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    public RankResponse response(Ranks ranks){
        RankResponse body = RankResponse.builder()
                .idx(ranks.getIdx())
                .rankName(ranks.getRankName())
                .build();
        return body;
    }
}
