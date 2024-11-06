package com.example.employee_service.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sm_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;

}
