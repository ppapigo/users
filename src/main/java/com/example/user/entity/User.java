package com.example.user.entity;

import com.example.user.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String username;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Role role = Role.USER;

    @Builder.Default
    @Column(name="active",nullable = false)
    private Boolean active = true;

    @Builder.Default
    @Column(nullable = false)
    private Boolean deleted = false;

    @Builder.Default
    @Column(name ="created_at" ,nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(name ="updated_at" ,nullable = false)
    private LocalDateTime updatedAt= LocalDateTime.now();


    public enum Role{
        ADMIN, MANAGER, USER
    }
    //User Entity가 인스턴스를 받아서 UserDTO를 반환
    public static UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserName(user.getUsername());
        return userDTO;
    }
}
