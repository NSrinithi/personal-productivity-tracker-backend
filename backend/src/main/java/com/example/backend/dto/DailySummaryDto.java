package com.example.backend.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DailySummaryDto {

    public Map<String, Double> getCategory() {
        return Category;
    }



    public void setCategory(Map<String, Double> category) {
        Category = category;
    }



    public DailySummaryDto(Map Category, LocalDate date, Boolean isStudied, Double totalHours, Long userId) {
        this.Category = Category;
        this.date = date;
        this.isStudied = isStudied;
        this.totalHours = totalHours;
        this.userId = userId;
    }

    
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    private Long userId;
    private LocalDate date;
    private Map<String,Double> Category;
    private Double totalHours;
    private Boolean isStudied;
    

    public DailySummaryDto() {
    }

    public Boolean getIsStudied() {
        return isStudied;
    }

    public void setIsStudied(Boolean isStudied) {
        this.isStudied = isStudied;
    }


    
}
