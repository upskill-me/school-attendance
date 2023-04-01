package me.upskill.schoolattendance.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClassesDTO {

    // list of classes
    // [{"name": "abc class", "number": 12}, "2", "3", "9", "10"]
    private List<Clazz> classes;


    @Getter
    @Setter
    public static class Clazz {
        private String name;

        private int number;
    }
}
