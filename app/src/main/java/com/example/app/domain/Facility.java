package com.example.app.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_FACILITY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"direction","title"})
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

    @OneToMany (mappedBy = "facility", fetch = FetchType.LAZY)
    private List<EmployeeFacility> employeeFacilities = new ArrayList<>();

    public static Facility of(String title, String direction) {
        return new Facility(null, title, direction, null);
    }

}
