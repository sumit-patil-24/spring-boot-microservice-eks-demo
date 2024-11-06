package com.example.user_service.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sm_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

}
