package com.example.backend.dto;

import java.time.LocalDate;

public class StudyResponsedto {
    private LocalDate date;
    private String topic;
    private int hours;
    private String notes;
    private String category;
    private Long id;

    public StudyResponsedto(Long id,String category, LocalDate date, int hours, String topic,String notes) {
        this.id=id;
        this.category = category;
        this.date = date;
        this.hours = hours;
        this.notes = notes;
        this.topic = topic;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    


    
}
