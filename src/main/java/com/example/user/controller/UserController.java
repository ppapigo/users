package com.example.user.controller;

import com.example.user.dto.UserDTO;
import com.example.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO user){
      try{
          UserDTO retUser = userService.createUser(user);
          return ResponseEntity.status(HttpStatus.CREATED).body(retUser);
      }catch (DataIntegrityViolationException e){
          return ResponseEntity.status(HttpStatus.CONFLICT).build();
      }


    }


    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> allUsers(){

        List<UserDTO> users = userService.allUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> user(@PathVariable Long id){
        try{
            UserDTO user = userService.getUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch(NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
