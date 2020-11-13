package com.example.study.repository;

import com.example.study.model.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail,Long> {
    Mail createMail(String userEmail, String userTitle, String userText);
    void mailSend(Mail mail);
}
