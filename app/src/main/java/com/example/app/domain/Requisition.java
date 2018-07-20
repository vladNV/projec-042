package com.example.app.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_REQUISITION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Requisition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private Long id;

    @Column(name = "description", length = 90)
    private String description;

    @Column(name = "from_date", nullable = false)
    @DateTimeFormat
    private LocalDateTime from;

    @Column(name = "till_date", nullable = false)
    @DateTimeFormat
    private LocalDateTime till;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
