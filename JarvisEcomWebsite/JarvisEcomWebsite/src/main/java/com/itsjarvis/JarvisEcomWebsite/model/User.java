package com.itsjarvis.JarvisEcomWebsite.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role;
}
