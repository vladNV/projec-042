package com.example.app.model;

import com.example.app.domain.Position;
import com.example.app.domain.TransportType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.Month;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BusinessTrip {
    private Long prime;
    private Long employeeId;

    @NotNull
    @JsonProperty("fullname")
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(max = 20)
    private String phone;

    @NotNull
    @Size(max = 25)
    private String qualification;

    @NotNull
    @Size(max = 20)
    @JsonProperty("passport")
    private String passportNumber;

    @NotNull
    private Position position;

    @NotNull
    @Size(max = 90)
    private String description;

    private LocalDateTime from;
    private LocalDateTime till;
    private LocalDateTime departureDate;

    @JsonProperty("rate")
    @NotNull
    @Size(max = 45)
    private String rateNumber;

    @JsonProperty("price")
    @NotNull
    @Max(1_000_000_000)
    private Long ticketPrice;

    @JsonProperty("type")
    @NotNull
    private TransportType transportType;

    @JsonProperty("transport")
    @NotNull
    @Size(max = 45)
    private String transportTitle;

    @JsonProperty("title")
    @NotNull
    @Size(max = 45)
    private String facilityTitle;

    @JsonProperty("direction")
    @NotNull
    @Size(max = 45)
    private String facilityDirection;

    @NotNull
    private JsDate fromDate;

    @NotNull
    private JsDate tillDate;

    @NotNull
    private JsDate departure;

    // data time from js
    @Getter
    @Setter
    public class JsDate {
        @NotNull
        @Max(9999)
        private int year;

        @NotNull
        @Max(12)
        private int month;

        @NotNull
        @Max(31)
        private int day;

        @NotNull
        @Max(23)
        private int hour;

        @NotNull
        @Max(59)
        private int minute;
    }

    public void clean() {
        this.from =
                LocalDateTime.of(
                this.fromDate.year, Month.of(this.fromDate.month), this.fromDate.day,
                this.fromDate.hour, this.fromDate.minute);
        this.till =
                LocalDateTime.of(
                this.tillDate.year, Month.of(this.tillDate.month), this.tillDate.day,
                this.tillDate.hour, this.tillDate.minute);
        this.departureDate =
                LocalDateTime.of(
                this.departure.year, Month.of(this.departure.month), this.departure.day,
                this.departure.hour, this.departure.minute);
    }

    @Deprecated
    public LocalDateTime during() {
        return till
                .minusYears(from.getYear())
                .minusMonths(from.getMonthValue())
                .minusDays(from.getDayOfMonth())
                .minusHours(from.getHour())
                .minusMinutes(from.getMinute());
    }


}
