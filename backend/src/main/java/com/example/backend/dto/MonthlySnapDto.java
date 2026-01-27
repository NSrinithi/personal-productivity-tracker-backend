package com.example.backend.dto;

public class MonthlySnapDto {
    private Double totalHours;
    private Integer studiedDays;
    private Integer missedDays;
    private Double averageHoursPer;
    public MonthlySnapDto(Double totalHours, Integer studiedDays, Integer missedDays, Double averageHoursPer) {
        this.totalHours = totalHours;
        this.studiedDays = studiedDays;
        this.missedDays = missedDays;
        this.averageHoursPer = averageHoursPer;
    }

    public MonthlySnapDto() {
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

    public Double getAverageHoursPer() {
        return averageHoursPer;
    }

    public void setAverageHoursPer(Double averageHoursPer) {
        this.averageHoursPer = averageHoursPer;
    }
    
}
