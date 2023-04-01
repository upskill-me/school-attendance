package me.upskill.schoolattendance.repository;

import me.upskill.schoolattendance.api.entities.jpa.Class;
import me.upskill.schoolattendance.api.entities.jpa.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends CrudRepository<Class, Long> {
}
