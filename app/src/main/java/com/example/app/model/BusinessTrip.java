package com.example.app.model;

import com.example.app.domain.Position;
import com.example.app.domain.TransportType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

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
    private static final int MAX_YEAR = 2100;
    private static final int MIN_YEAR = 2018;

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
        validateDate(fromDate);
        validateDate(tillDate);
        validateDate(departure);

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

    private void validateDate(JsDate jsDate) {
        validateDate(jsDate.year, jsDate.month, jsDate.day, jsDate.hour, jsDate.minute);
    }

    private void validateDate(int year, int month, int day, int hour, int minute) {
        validateYear(year);
        checkDayAndMonth(day, month, year);
        checkMinuteAndHour(hour, minute);
    }

    private void checkMinuteAndHour(int hour, int minute) {
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new ValidationException("Incorrect minutes (and) or hours!");
        }
    }

    private void checkDayAndMonth(int day, int month, int year) {
        if (month > 12 || month < 1) {
            throw new ValidationException("Incorrect month!");
        }

        List<Integer> month31 = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
        List<Integer> month30 = Arrays.asList(4, 6, 9, 11);
        int february = 2;

        if (month31.contains(month) && !isCorrectDay(day, 31)) {
            throw new ValidationException("Incorrect day!");
        }

        if (month30.contains(month) && !isCorrectDay(day, 30)) {
            throw new ValidationException("Incorrect day!");
        }

        if (month == february && !isCorrectDay(day, isLeapYear(year) ? 29 : 28)) {
            throw new ValidationException("Incorrect day!");
        }
    }

    private boolean isCorrectDay(int day, int max) {
        return day > 0 && day <= max;
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private void validateYear(int year) {
        if (year < MIN_YEAR || year > MAX_YEAR) {
            throw new ValidationException("Incorrect year!");
        }
    }

    public LocalDateTime during() {
        return till
                .minusYears(from.getYear())
                .minusMonths(from.getMonthValue())
                .minusDays(from.getDayOfMonth())
                .minusHours(from.getHour())
                .minusMinutes(from.getMinute());
    }
}
