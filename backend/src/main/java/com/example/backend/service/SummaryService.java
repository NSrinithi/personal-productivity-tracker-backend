package com.example.backend.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.backend.dto.DailySummaryDto;
import com.example.backend.dto.DaySummaryDto;
import com.example.backend.dto.MonthlySummaryDto;
import com.example.backend.dto.StudyResponsedto;
import com.example.backend.dto.WeeklySummaryDto;
import com.example.backend.entity.StudyLog;
import com.example.backend.entity.User;
import com.example.backend.repo.StudyLogRepo;
import com.example.backend.repo.UserRepo;


@Service
public class SummaryService {

    private StudyLogRepo str;
    private UserRepo ur;

    public SummaryService(StudyLogRepo str,UserRepo ur) {
        this.str = str;
        this.ur=ur;
    }


    public WeeklySummaryDto summary(LocalDate date){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user=ur.findByEmail(email);
        WeeklySummaryDto week=new WeeklySummaryDto();
        LocalDate start=date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate end=start.plusDays(6);
        List<StudyLog> list=str.findByUserIdAndDateBetween(user.getId(), start, end);
        double totalHours=list.stream().mapToDouble(u->u.getHours()).sum();
        Set<LocalDate> uniqueDays=list.stream().map(u->u.getDate()).collect(Collectors.toSet());
        Map<String,Double>map=list.stream().collect(Collectors.groupingBy(StudyLog::getCategory,Collectors.summingDouble(StudyLog::getHours)));
        // week.setUserId(userId);
        week.setWeekStart(start);
        week.setWeekEnd(end);
        week.setStudiedDays(uniqueDays.size());
        week.setMissedDays(7-uniqueDays.size());
        week.setTotalHoursStudied(totalHours);
        week.setCategory(map);
        return week;
    }

    public DaySummaryDto dayWise(LocalDate date){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        DaySummaryDto day=new DaySummaryDto();
        User u=ur.findByEmail(email);
        day.setDate(date);
        List<StudyResponsedto> logs=str.findByUserIdAndDate(u.getId(),date).stream().map(y->new StudyResponsedto(y.getId(),y.getCategory(), y.getDate(), y.getHours(),y.getTopic(),y.getNotes())).toList();
        day.setLog(logs);
        double hours=logs.stream().mapToDouble(x->x.getHours()).sum();
        day.setTotalHours(hours);
        return day;
    }


    public DailySummaryDto dailySummary(LocalDate date){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        DailySummaryDto daily=new DailySummaryDto();
        User u=ur.findByEmail(email);
        daily.setUserId(u.getId());
        daily.setDate(date);
        List<StudyLog> list=str.findByUserIdAndDate(daily.getUserId(),daily.getDate());
        Map<String,Double>map=list.stream().collect(Collectors.groupingBy(StudyLog::getCategory,Collectors.summingDouble(StudyLog::getHours)));
        daily.setCategory(map);
        double hours=list.stream().mapToDouble(x->x.getHours()).sum();
        if(hours>0) daily.setIsStudied(true);
        else daily.setIsStudied(false);
        daily.setTotalHours(hours);
        return daily;

    }

    public MonthlySummaryDto getMonthlySummary(int year,int month){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user=ur.findByEmail(email);
        LocalDate start=LocalDate.of(year,month, 1);
        LocalDate end=start.with(TemporalAdjusters.lastDayOfMonth());
        List<StudyLog> logs=str.findByUserIdAndDateBetween(user.getId(), start, end);
        Double totalHours=logs.stream().mapToDouble(x->x.getHours()).sum();
        Set<LocalDate> uniqueDates=logs.stream().map(y->y.getDate()).collect(Collectors.toSet());
        Integer studiedDays=uniqueDates.size();
        YearMonth yearMonth=YearMonth.of(year, month);
        int monthlyDays=yearMonth.lengthOfMonth();
        MonthlySummaryDto monthly=new MonthlySummaryDto();
        monthly.setMissedDays(monthlyDays-studiedDays);
        monthly.setStudiedDays(studiedDays);
        monthly.setTotalHours(totalHours);
        monthly.setAverageHoursPerDay(Math.round((totalHours/monthlyDays)*100.0)/100.0);
        monthly.setMonth(yearMonth);
        return monthly;
    }
}
