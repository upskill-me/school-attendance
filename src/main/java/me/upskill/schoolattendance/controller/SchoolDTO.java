package me.upskill.schoolattendance.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SchoolDTO {

    // name
    private String name;

    // address
    private String address;

    // classes in school
    private ClassesDTO classes;
}
