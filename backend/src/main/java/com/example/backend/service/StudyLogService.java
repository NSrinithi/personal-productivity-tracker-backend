package com.example.backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.backend.dto.StudyRequestdto;
import com.example.backend.dto.StudyResponsedto;
import com.example.backend.entity.StudyLog;
import com.example.backend.entity.User;
import com.example.backend.exception.ResourceNotFound;
import com.example.backend.repo.StudyLogRepo;
import com.example.backend.repo.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Service
public class StudyLogService {
    private StudyLogRepo str;
    private UserRepo ur;

    public StudyLogService(StudyLogRepo str,UserRepo ur) {
        this.str = str;
        this.ur=ur;
    }

    public Page<StudyResponsedto> getByUser(int Page,int siZe){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User u=ur.findByEmail(email);
        Pageable pageable=PageRequest.of(Page,siZe,Sort.by("date").descending());
        return str.findByUser(u,pageable).map(x->new StudyResponsedto(x.getId(),x.getCategory(), x.getDate(), x.getHours(),x.getTopic(),x.getNotes()));
    }

    public StudyResponsedto create(StudyRequestdto s){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User u=ur.findByEmail(email);
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

    public List<StudyResponsedto> getByDate(LocalDate date){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user=ur.findByEmail(email);
        return str.findByUserIdAndDate(user.getId(), date).stream().map(u->new StudyResponsedto(u.getId(), u.getCategory(), u.getDate(), u.getHours(),u.getTopic(), u.getNotes())).toList();
    }

    public List<StudyResponsedto> searchByCategory(String category){
        return str.findByCategoryContaining(category).stream().map(u->new StudyResponsedto(u.getId(), u.getCategory(), u.getDate(), u.getHours(),u.getTopic(), u.getNotes())).toList();
    }

    public List<StudyResponsedto> findByuser(){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user=ur.findByEmail(email);
        List<StudyLog> list=str.findByUserId(user.getId());
        for(StudyLog s:list){
            System.out.println(s.getTopic());
            System.out.println("Notes:"+s.getNotes());
        }
        return list.stream().map(u->new StudyResponsedto(u.getId(), u.getCategory(), u.getDate(), u.getHours(), u.getNotes(),u.getTopic())).toList();
    }


    public String deleteStudyLog(long id){
        StudyLog study=str.findById(id).orElseThrow(()->new ResourceNotFound("Resource not found"));
        str.deleteById(id);
        return "Deleted successfully";
    }


    public StudyResponsedto getById(Long id){
        StudyLog s=str.findById(id).orElseThrow(()->new ResourceNotFound("Not found with id:"+id));
        return new StudyResponsedto(s.getId(), s.getCategory(), s.getDate(), s.getHours(), s.getNotes(), s.getTopic());
    }

    public StudyResponsedto updateById(Long id,StudyRequestdto request){
        StudyLog s=str.findById(id).orElseThrow(()->new ResourceNotFound("Not found with id:"+id));
        s.setCategory(request.getCategory());
        s.setDate(request.getDate());
        s.setHours(request.getHours());
        s.setTopic(request.getTopic());
        s.setNotes(request.getNotes());
        StudyLog study=str.save(s);
        return new StudyResponsedto(study.getId(), study.getCategory(), study.getDate(),s.getHours(), study.getNotes(), study.getTopic());
    }
}
