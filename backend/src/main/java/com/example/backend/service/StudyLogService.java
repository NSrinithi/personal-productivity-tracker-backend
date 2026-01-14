package com.example.backend.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.backend.dto.StudyRequestdto;
import com.example.backend.dto.StudyResponsedto;
import com.example.backend.dto.WeeklySummaryDto;
import com.example.backend.entity.StudyLog;
import com.example.backend.entity.User;
import com.example.backend.exception.ResourceNotFound;
import com.example.backend.repo.StudyLogRepo;
import com.example.backend.repo.UserRepo;

@Service
public class StudyLogService {
    private StudyLogRepo str;
    private UserRepo ur;

    public StudyLogService(StudyLogRepo str,UserRepo ur) {
        this.str = str;
        this.ur=ur;
    }

    public StudyResponsedto create(StudyRequestdto s){
        User u=ur.findById(s.getUserId()).orElseThrow(()-> new ResourceNotFound("User Not found with id:"+s.getUserId()));
        StudyLog sl=new StudyLog();
        sl.setCategory(s.getCategory());
        sl.setDate(s.getDate());
        sl.setHours(s.getHours());
        sl.setNotes(s.getNotes());
        sl.setTopic(s.getTopic());
        sl.setU(u);
        StudyLog saved=str.save(sl);
        return new StudyResponsedto(saved.getId(),saved.getCategory(),saved.getDate(),saved.getHours(),saved.getTopic(),saved.getNotes());
    }

    public List<StudyResponsedto> getAll(){
        return str.findAll().stream().map(u->new StudyResponsedto(u.getId(),u.getCategory(), u.getDate(), u.getHours(),u.getTopic(),u.getNotes())).toList();
    }

    public List<StudyResponsedto> getByDate(Long userId,LocalDate date){
        return str.findByUserIdAndDate(userId, date).stream().map(u->new StudyResponsedto(u.getId(), u.getCategory(), u.getDate(), u.getHours(),u.getTopic(), u.getNotes())).toList();
    }

    public List<StudyResponsedto> searchByCategory(String category){
        return str.findByCategoryContaining(category).stream().map(u->new StudyResponsedto(u.getId(), u.getCategory(), u.getDate(), u.getHours(),u.getTopic(), u.getNotes())).toList();
    }

    public List<StudyResponsedto> findByuser(Long userId){
        return str.findByUserId(userId).stream().map(u->new StudyResponsedto(u.getId(), u.getCategory(), u.getDate(), u.getHours(),u.getTopic(), u.getNotes())).toList();
    }


    public WeeklySummaryDto summary(Long userId,LocalDate date){
        WeeklySummaryDto week=new WeeklySummaryDto();
        LocalDate start=date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate end=start.plusDays(6);
        List<StudyLog> list=str.findByUserIdAndDateBetween(userId, start, end);
        int totalHours=list.stream().mapToInt(u->u.getHours()).sum();
        Set<LocalDate> uniqueDays=list.stream().map(u->u.getDate()).collect(Collectors.toSet());
        Map<String,Integer>map=list.stream().collect(Collectors.groupingBy(StudyLog::getCategory,Collectors.summingInt(StudyLog::getHours)));
        week.setUserId(userId);
        week.setWeekStart(start);
        week.setWeekEnd(end);
        week.setStudiedDays(uniqueDays.size());
        week.setMissedDays(7-uniqueDays.size());
        week.setTotalHoursStudied(totalHours);
        week.setCategory(map);
        return week;
    }
    

}
