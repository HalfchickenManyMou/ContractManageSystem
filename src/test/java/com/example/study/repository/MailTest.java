package com.example.study.repository;

import com.example.study.model.entity.Mail;
import com.example.study.service.MailLogicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Autowired
    private MailLogicService malService;
    @Autowired
    MailRepository mailRepository;
    @Test
    public void mailsend(){
        String text="testOK";
        malService.mailSender(text);

    }
    @Test
    public void create(){
        Mail mail = Mail.builder()
            .sender("test@naver.com")
            .title("testMail")
            .content("content")
            .sendDate(LocalDateTime.now())
            .build();
        System.out.println("---------------mail Hibernate-----------------");
        mailRepository.save(mail);
    }
}
