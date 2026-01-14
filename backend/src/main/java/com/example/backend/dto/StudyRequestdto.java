package com.example.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudyRequestdto {

    @NotNull(message="Date cannot be null")
    private LocalDate date;
    @NotBlank(message="Topic cannot be empty")
    private String topic;

    private int hours;
    private String notes;

    @NotBlank(message="category cannot be empty")
    private String category;

    @NotNull(message="User Id cannot be null")
    private Long userId;

    public StudyRequestdto(String category, LocalDate date, int hours, String topic,Long userId,String notes) {
        this.category = category;
        this.date = date;
        this.hours = hours;
        this.notes = notes;
        this.topic = topic;
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    
}
