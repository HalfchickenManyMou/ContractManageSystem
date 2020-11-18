package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.MailRequest;
import com.example.study.model.network.response.MailResponse;
import com.example.study.service.MailLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MailApiController implements CrudInterface<MailRequest,MailResponse> {

    @Autowired
    MailLogicService mailLogicService;
    public void mailSend(){
        
    }

    @Override
    public Header<MailResponse> create(Header<MailRequest> request) {
        return mailLogicService.create(request);
    }

    @Override
    public Header<MailResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<MailResponse> update(Header<MailRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}