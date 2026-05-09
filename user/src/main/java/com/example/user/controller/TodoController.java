package com.example.user.controller;

import com.example.user.dto.RequestTodo;
import com.example.user.dto.ResponseTodo;
import com.example.user.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/new")
    public ResponseEntity<ResponseTodo> create(@RequestBody @Valid RequestTodo todo){
        try{
            ResponseTodo resTodo = todoService.create(todo);
            return new ResponseEntity.status(HttpStatus.OK).body(resTodo);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
