package com.example.backend.dto;

import java.time.LocalDate;
import java.time.YearMonth;

public class MonthlySummaryDto {
    
    private YearMonth month;
    private Double totalHours;
    private Integer studiedDays;
    private Integer missedDays;
    private Double averageHoursPerDay;

    public MonthlySummaryDto(Double averageHoursPerDay, Integer missedDays, YearMonth month, Integer studiedDays, Double totalHours) {
        this.averageHoursPerDay = averageHoursPerDay;
        this.missedDays = missedDays;
        this.month = month;
        this.studiedDays = studiedDays;
        this.totalHours = totalHours;
    }

    public MonthlySummaryDto() {
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getStudiedDays() {
        return studiedDays;
    }

    public void setStudiedDays(Integer studiedDays) {
        this.studiedDays = studiedDays;
    }

    public Integer getMissedDays() {
        return missedDays;
    }

    public void setMissedDays(Integer missedDays) {
        this.missedDays = missedDays;
    }

    public Double getAverageHoursPerDay() {
        return averageHoursPerDay;
    }

    public void setAverageHoursPerDay(Double averageHoursPerDay) {
        this.averageHoursPerDay = averageHoursPerDay;
    }



}
