package me.upskill.schoolattendance.service;

import me.upskill.schoolattendance.controller.SchoolDTO;

public interface SchoolService {

    // api to create school
    void createSchool(SchoolDTO dto);
}
