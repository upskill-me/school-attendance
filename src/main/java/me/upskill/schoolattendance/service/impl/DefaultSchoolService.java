package me.upskill.schoolattendance.service.impl;

import jakarta.transaction.Transactional;
import me.upskill.schoolattendance.api.ContextHolder;
import me.upskill.schoolattendance.api.entities.jpa.Class;
import me.upskill.schoolattendance.api.entities.jpa.School;
import me.upskill.schoolattendance.controller.ClassesDTO;
import me.upskill.schoolattendance.controller.SchoolDTO;
import me.upskill.schoolattendance.repository.ClassRepository;
import me.upskill.schoolattendance.repository.SchoolRepository;
import me.upskill.schoolattendance.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultSchoolService implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private ClassRepository classRepository;

    @Override
    @Transactional
    public void createSchool(SchoolDTO dto) {
        long userId = ContextHolder.getUserId();
        // header jwt token
        School entity = new School();
        entity.setName(dto.getName());
        entity.setUserId(userId);
        entity.setAddress(dto.getAddress());

        // save
        School saved = schoolRepository.save(entity);

        List<Class> classes = new ArrayList<>();

        for (ClassesDTO.Clazz cdto : dto.getClasses().getClasses()) {
            Class cEntity= new Class();
            cEntity.setName(cdto.getName());
            cEntity.setNumber(cdto.getNumber());
            cEntity.setSchoolId(saved.getId());
            classes.add(cEntity);
        }

        classRepository.saveAll(classes);
    }
}
