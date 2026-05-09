package com.example.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=100)
    private String title;

    @Column(nullable = false, length=200)
    private String todo;

    @Column(nullable = false, length=50)
    private String category;

    @Builder.Default
    @Column(nullable = false)
    private Boolean deleted =false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean done =false;

    @Builder.Default
    @Column(name ="created_at" ,nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(name ="deleted_at" ,nullable = false)
    private LocalDateTime deletedAt= LocalDateTime.now();
}
