package com.example.study.model.entity;

import com.sun.javafx.beans.IDProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Accessors(chain = true)
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;
    private String sender;
    private String title;
    private String content;
    private LocalDateTime sendDate;

}
