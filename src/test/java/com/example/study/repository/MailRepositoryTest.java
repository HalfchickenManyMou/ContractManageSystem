package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Mail;
import com.example.study.service.MailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
public class MailRepositoryTest extends StudyApplicationTests {
    @Autowired
    MailRepository mailRepository;
    private JavaMailSender mailSender;

    @Test
    public void createMail() {
        Mail mail = new Mail();
        mail.setTitle("test1");
        mail.setAddress("test3");
        mail.setContent("");

    }

    @Test
    public void mailSend() {
        System.out.println("이메일 전송 완료");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("shkimm5189@naver.com"); //보내는 사람 주소
        message.setFrom("shkimm5189@gamil.com"); //받는사람주소
        message.setSubject("test1");
        message.setText("contentTest");
        mailSender.send(message);
    }
}
