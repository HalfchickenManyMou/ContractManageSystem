package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Mail;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.MailRequest;
import com.example.study.model.network.response.MailResponse;
import com.example.study.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class InquireController implements CrudInterface<MailRequest, MailResponse> {
    @Autowired
    MailService mailService;
    @GetMapping("")
    public String dispMail(){
        return "inquire";
    }

    @PostMapping("/inquire")
    public @ResponseBody void execMail(@RequestBody Map<String, String> mailInfo){
        System.out.println("이메일 전송 완료1");
        Mail mail = mailService.createMail(mailInfo.get("userEmail"),mailInfo.get("userTitle"),mailInfo.get("userText"));
        mailService.mailSend(mail);
    }

    @Override
    public Header<MailResponse> create(Header<MailRequest> request) {
        return null;
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
