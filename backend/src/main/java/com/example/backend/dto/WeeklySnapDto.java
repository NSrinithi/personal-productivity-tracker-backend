package com.example.backend.dto;

public class WeeklySnapDto {
    private Double totalHours;
    private Integer studiedDays;
    private Integer missedDays;

    public WeeklySnapDto(Integer missedDays, Integer studiedDays, Double totalHours) {
        this.missedDays = missedDays;
        this.studiedDays = studiedDays;
        this.totalHours = totalHours;
    }

    public WeeklySnapDto() {
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



}
