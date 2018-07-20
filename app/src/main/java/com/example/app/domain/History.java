package com.example.app.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_LOG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "log_id")
    private Long id;

    @Column(name = "ip_address")
    private String IP;

    @Column(nullable = false, name = "action_date")
    @DateTimeFormat
    private LocalDateTime actionDate;

    @Column(nullable = false, name = "action_info", length = 8000)
    private String information;

    @Column(nullable = false, name = "author", length = 1000)
    private String author;

}
