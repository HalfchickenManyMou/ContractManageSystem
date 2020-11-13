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
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService implements CrudInterface<MailRequest, MailResponse> {

    @Autowired
    private JavaMailSender mailSender;
    private static final String TO_ADDRESS = "shkimm5189@gmail.com";



    public Mail createMail(String userEmail, String userTitle, String userText){
        Mail mail = new Mail();
        mail.setAddress(userEmail);
        mail.setTitle(userTitle);
        mail.setContent(userText);
        return mail;
    }
    public void mailSend(Mail mail){
        System.out.println("이메일 전송 완료");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress()); //보내는 사람 주소
        message.setFrom(MailService.TO_ADDRESS); //받는사람주
        message.setSubject(mail.getTitle());
        message.setText(mail.getContent());
        mailSender.send(message);
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
