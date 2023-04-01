package me.upskill.schoolattendance.repository;

import me.upskill.schoolattendance.api.entities.jpa.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    // find all roles for this user id
    List<Role> findAllByUserId(long userId);
}
