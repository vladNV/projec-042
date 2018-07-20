package com.example.app.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login", length = 100, nullable = false, unique = true)
    private String login;

    @Column(name = "passsword", length = 8000, nullable = false)
    private String password;

    @Column(name = "registration", nullable = false)
    @DateTimeFormat
    private LocalDateTime registrationDate;

    @Column(name = "role", nullable = false, length = 100)
    private String roleString;

}
