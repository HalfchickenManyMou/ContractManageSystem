package com.example.study.model.enumclass;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum AuthType {
    read(0,"읽기","읽기 권한"),
    write(1,"수정","수정 권한");

    private Integer id;
    private String title;
    private String description;
}
