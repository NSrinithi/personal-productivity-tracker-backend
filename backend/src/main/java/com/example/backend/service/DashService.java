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

import com.example.backend.dto.DashBoardDto;
import com.example.backend.dto.MonthlySnapDto;
import com.example.backend.dto.StreakDto;
import com.example.backend.dto.WeeklySnapDto;
import com.example.backend.entity.StudyLog;
import com.example.backend.entity.User;
import com.example.backend.repo.StudyLogRepo;
import com.example.backend.repo.UserRepo;


@Service
public class DashService {


    private StudyLogRepo str;
    private UserRepo ur;
    private StreakService ss;


    public DashService(StreakService ss, StudyLogRepo str, UserRepo ur) {
        this.ss = ss;
        this.str = str;
        this.ur = ur;
    }

    public DashBoardDto dash(){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user=ur.findByEmail(email);
        LocalDate currentDate =LocalDate.now();
        LocalDate weekStart=currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate weekEnd=weekStart.plusDays(6);
        YearMonth yearMonth=YearMonth.from(currentDate);
        LocalDate monthStart=yearMonth.atDay(1);
        LocalDate monthEnd=yearMonth.atEndOfMonth();
        DashBoardDto dash=new DashBoardDto();
        dash.setToday(currentDate);
        List<StudyLog> listPerDay=str.findByUserIdAndDate(user.getId(),currentDate);
        double hours=listPerDay.stream().mapToDouble(x->x.getHours()).sum();
        if(hours>0) dash.setIsStudied(true);
        else dash.setIsStudied(false);
        dash.setTotalHours(hours);
        // List<StudyLog> log=str.findByUserId(user.getId());
        // Set<LocalDate> uniqueDays=log.stream().map(u->u.getDate()).collect(Collectors.toSet());
        // LocalDate today=LocalDate.now();
        // int streak=0;
        // while (uniqueDays.contains(today)) { 
        //     streak++;
        //     today=today.minusDays(1);  
        // }
        // List<LocalDate> sortedDate=uniqueDays.stream().sorted().toList();
        // int currentRun=1;
        // int max=1;
        // LocalDate startingDate=sortedDate.get(0);
        // for(int i=0;i<sortedDate.size();i++){
        //     LocalDate current=sortedDate.get(i);
        //     if(current.equals(startingDate.plusDays(1))){
        //         currentRun++;
        //     }
        //     else{
        //         currentRun=1;
        //     }
        //     max=Math.max(currentRun,max);
        //     startingDate=current;
        // }
        StreakDto streak=ss.getStreak();
        dash.setCurrentStreak(streak.getCurrentStreak());
        dash.setLongestStreak(streak.getLongestStreak());
        dash.setLastStudiedDate(streak.getLastStudiedDate());
        List<StudyLog> getCategory=str.findByUserIdAndDateBetween(user.getId(),monthStart, monthEnd);
        Map<String,Double>map=getCategory.stream().collect(Collectors.groupingBy(StudyLog::getCategory,Collectors.summingDouble(StudyLog::getHours)));
        dash.setCategories(map);
        List<StudyLog> listPerWeek=str.findByUserIdAndDateBetween(user.getId(), weekStart, weekEnd);
        Set<LocalDate> uniqueDaysPerweek=listPerWeek.stream().map(u->u.getDate()).collect(Collectors.toSet());
        double totalHoursPerweek=listPerWeek.stream().mapToDouble(u->u.getHours()).sum();
        WeeklySnapDto weekly=new WeeklySnapDto();
        weekly.setTotalHours(totalHoursPerweek);
        weekly.setStudiedDays(uniqueDaysPerweek.size());
        weekly.setMissedDays(7-weekly.getStudiedDays());
        dash.setWeekly(weekly);
        MonthlySnapDto monthly=new MonthlySnapDto();
        List<StudyLog> listPerMonth=str.findByUserIdAndDateBetween(user.getId(),monthStart, monthEnd);
        Set<LocalDate> uniqueDaysPerMonth=listPerMonth.stream().map(b->b.getDate()).collect(Collectors.toSet());
        double totalHoursPerMonth=listPerMonth.stream().collect(Collectors.summingDouble(x->x.getHours()));
        monthly.setTotalHours(totalHoursPerMonth);
        int monthDays=yearMonth.lengthOfMonth();
        monthly.setStudiedDays(uniqueDaysPerMonth.size());
        monthly.setMissedDays(monthDays-uniqueDaysPerMonth.size());
        monthly.setAverageHoursPer(Math.round((totalHoursPerMonth/monthDays)*100.0)/100.0);
        dash.setMonthly(monthly);
        return dash;
    }
}
