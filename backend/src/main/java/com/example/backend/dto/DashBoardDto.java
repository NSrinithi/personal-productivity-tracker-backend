package com.example.backend.dto;

import java.time.LocalDate;
import java.util.Map;

public class DashBoardDto {
    
    private LocalDate today;
    private boolean isStudied;
    private Double totalHours;
    private Integer currentStreak;
    private Integer longestStreak;
    private LocalDate lastStudiedDate;
    private Map<String,Double> Categories;
    private WeeklySnapDto weekly;
    private MonthlySnapDto monthly;

    public DashBoardDto(Map<String, Double> Categories, Integer currentStreak, boolean isStudied, LocalDate lastStudiedDate, Integer longestStreak, MonthlySnapDto monthly, LocalDate today, Double totalHours, WeeklySnapDto weekly) {
        this.Categories = Categories;
        this.currentStreak = currentStreak;
        this.isStudied = isStudied;
        this.lastStudiedDate = lastStudiedDate;
        this.longestStreak = longestStreak;
        this.monthly = monthly;
        this.today = today;
        this.totalHours = totalHours;
        this.weekly = weekly;
    }

    public DashBoardDto() {
    }

    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public boolean isIsStudied() {
        return isStudied;
    }

    public void setIsStudied(boolean isStudied) {
        this.isStudied = isStudied;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(Integer currentStreak) {
        this.currentStreak = currentStreak;
    }

    public Integer getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(Integer longestStreak) {
        this.longestStreak = longestStreak;
    }

    public LocalDate getLastStudiedDate() {
        return lastStudiedDate;
    }

    public void setLastStudiedDate(LocalDate lastStudiedDate) {
        this.lastStudiedDate = lastStudiedDate;
    }

    public Map<String, Double> getCategories() {
        return Categories;
    }

    public void setCategories(Map<String, Double> Categories) {
        this.Categories = Categories;
    }

    public WeeklySnapDto getWeekly() {
        return weekly;
    }

    public void setWeekly(WeeklySnapDto weekly) {
        this.weekly = weekly;
    }

    public MonthlySnapDto getMonthly() {
        return monthly;
    }

    public void setMonthly(MonthlySnapDto monthly) {
        this.monthly = monthly;
    }

    
}
