package com.example.user.service;

import com.example.user.dto.RequestTodo;
import com.example.user.dto.ResponseTodo;
import com.example.user.entity.Todo;
import com.example.user.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public ResponseTodo create(RequestTodo reqTodo) throws DataIntegrityViolationException {
       Todo todo = Todo.builder()
               .title(reqTodo.getTitle())
               .todo(reqTodo.getTodo())
               .category(reqTodo.getCategory())
               .done(reqTodo.getDone())
               .build();
        Todo savedTodo =todoRepository.save( todo);

        return Todo.toTodoDTO(savedTodo);
    }
}
