package com.example.study.controller.api;

import com.example.study.model.entity.Ranks;
import com.example.study.model.network.Header;
import com.example.study.model.network.response.RankResponse;
import com.example.study.service.RankApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RankApiController{

    @Autowired
    RankApiLogicService rankApiLogicService;

    @PostMapping("/rank/all")
    public Header<List<Ranks>> allDeleteAndCreate(@RequestBody Header<List<Ranks>> request) {
        return rankApiLogicService.allDeleteAndCreate(request);
    }

    @GetMapping("/rank")
    public Header<List<RankResponse>> readAll() {
        return rankApiLogicService.readAll();
    }

}
