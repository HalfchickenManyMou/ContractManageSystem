package com.example.study.service;

import com.example.study.ifs.CrudInterface;

import com.example.study.model.entity.Mail;

import com.example.study.model.network.Header;
import com.example.study.model.network.request.MailRequest;
import com.example.study.model.network.response.MailResponse;

import com.example.study.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class MailLogicService implements CrudInterface<MailRequest, MailResponse> {
    @Autowired
    MailRepository MailRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
//
//    private String build(Mail mail){
//        Context context = new Context();
//
//        context.setVariable("title",mail.getTitle());
//        context.setVariable("address",mail.getSender());
//        context.setVariable("content",mail.getContent());
//        return templateEngine.process("inquirePage",context);
//    }

    public void mailSender(Header<MailRequest> request) {
        MailRequest mailRequest = request.getData();
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            //setTo :받는 사람, setFrom 보내는 사람

            messageHelper.setFrom("shkimm5189@naver.com");
            messageHelper.setTo(mailRequest.getSender());
            messageHelper.setSubject(mailRequest.getTitle());
            messageHelper.setText(mailRequest.getContent());
        };
        mailSender.send(messagePreparator);
    }

    @Override
    public Header<MailResponse> create(Header<MailRequest> request) {
        MailRequest mailRequest = request.getData();
        Mail mail = Mail.builder()
                .sender(mailRequest.getSender())
                .title(mailRequest.getTitle())
                .content(mailRequest.getContent())
                .sendDate(LocalDateTime.now())
                .build();
        Mail sendInfo = MailRepository.save(mail);
        MailResponse res = response(sendInfo);
        return Header.OK(res);
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

    private MailResponse response(Mail mail) {
        MailResponse body = MailResponse.builder()
                .sender(mail.getSender())
                .title(mail.getTitle())
                .content(mail.getContent())
                .sendDate(mail.getSendDate())
                .build();
        return body;
    }
}
