package com.example.app.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "T_FACILITY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "facility_id")
    private Long id;

    @Searchable(minLength = 3)
    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Searchable(minLength = 4)
    @Column(name = "direction", nullable = false, length = 45)
    private String direction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;


}
