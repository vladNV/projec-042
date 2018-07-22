package com.example.app.domain.view;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
@Immutable
@Table(name = "business_trip")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "prime")
public class BusinessTripView {
    @Id
    private Long prime;

    @Column(name = "transport_id")
    private Long transportId;

    @Column(name = "employee_id")
    private Long employeeId;

    private String fullname;
    private String passport;
    private String phone;
    private Integer position;
    private String qualification;

    @Column(name = "request_id")
    private Long requestId;

    private String description;

    @Column(name = "from_date")
    private LocalDateTime from;

    @Column(name = "till_date")
    private LocalDateTime till;

    @Column(name = "facility_id")
    private Long facilityId;

    @Column(name = "trip_id")
    private Long tripId;

    private String direction;
    private String title;

    private LocalDateTime departure;

    @Column(name = "trip_code")
    private String tripCode;

    private Long cost;

    @Column(name = "type")
    private Integer transportType;

    @Column(name = "transport_name")
    private String transportName;

}
