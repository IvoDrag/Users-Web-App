package com.ivodrag.springbootusers.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, length = 20)
    private String password;
    @Column(nullable = false, length = 50, name="first_name")
    private String firstName;
    @Column(nullable = false, length = 50, name="last_name")
    private String lastName;
}
