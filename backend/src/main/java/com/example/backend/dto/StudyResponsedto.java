package com.example.backend.dto;

import java.time.LocalDate;

public class StudyResponsedto {
    private Long id;

    private LocalDate date;

    private String topic;

    private String category;

    private Double hours;

    private String notes;

    

    public StudyResponsedto() {
    }

    public StudyResponsedto(Long id,String category, LocalDate date, Double hours,  String notes, String topic) {
        this.id = id;
        this.category = category;
        this.date = date;
        this.hours = hours;
        this.notes = notes;
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

     public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
