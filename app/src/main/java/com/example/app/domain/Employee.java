package com.example.app.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_EMPLOYEE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "fullname", nullable = false, length = 30)
    @Searchable
    private String fullname;

    @Column(name = "position", nullable = false, length = 15)
    @Enumerated
    private Position position;

    @Column(name = "phone", nullable = false, length = 20)
    @Searchable
    private String phone;

    @Column(name = "qualification", nullable = false, length = 25)
    private String qualification;

    @Column(name = "passport", nullable = false, length = 30)
    @Searchable
    private String passport;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Requisition> requisitions = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Trip> trips = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Facility> facilities = new ArrayList<>();

}
