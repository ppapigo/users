package com.example.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor  //매개 변수가 없는 생성자
@AllArgsConstructor //private 멤버 데이터 요소를 매개 변수로 갖는 생성자

public class ResponseTodo {

    //id
    private Long id;

    //RequestTodo 에 있는 모든 항목 포함
    private String title;
    private String todo;
    private String category;
    private Boolean done;

    //created_at
    private String createdAt;
}
