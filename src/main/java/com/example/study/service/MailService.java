package com.example.study.service;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Mail;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.MailRequest;
import com.example.study.model.network.response.MailResponse;
import com.example.study.repository.MailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    private static final String TO_ADDRESS = "shkimm5189@gmail.com";




    public void mailSend() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message , true);

        helper.setSubject("TEST1");
        helper.setTo("shkimm5189@naver.com");
        helper.setText("TEST");
        mailSender.send(message);
    }


}
