package com.example.app.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_TRIP")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "rateNumber")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trip_id")
    private Long id;

    @Column(name = "departure", nullable = false)
    @DateTimeFormat
    private LocalDateTime departure;

    @Column(name = "trip_code", nullable = false, length = 45)
    private String rateNumber;

    @Column(name = "cost", nullable = false)
    private Long ticketPrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "transport_id")
    private Transport transport;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Requisition requisition;
}
