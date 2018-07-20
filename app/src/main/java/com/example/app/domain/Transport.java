package com.example.app.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_TRANSPORT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transport_id")
    private Long id;

    @Column(name = "type", nullable = false)
    @Enumerated
    private TransportType type;

    @Column(name = "transport_name", nullable = false, length = 45)
    private String title;

    @OneToMany(mappedBy = "transport", fetch = FetchType.LAZY)
    private List<Trip> trips = new ArrayList<>();

}
