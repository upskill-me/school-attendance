package me.upskill.schoolattendance.controller;

import jakarta.servlet.http.HttpServletRequest;
import me.upskill.schoolattendance.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * School apis
 */
@RestController
@RequestMapping("/api/v1")
public class SchoolController extends AbstractController {

    @Autowired
    private SchoolService schoolService;

    // all logged in users should be able to call this api
    @PostMapping(value = "/schools", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @AuthRequirement()
    public ResponseEntity<?> createSchool(HttpServletRequest request, @RequestBody SchoolDTO body) {
        assertValid(body.getName(), "name is mandatory");
        assertValid(body.getAddress(), "address is mandatory");
        assertNotNull(body.getClasses(), "classes is mandatory");
        assertNotNull(body.getClasses().getClasses(), "classes is mandatory");
        schoolService.createSchool(body);

        return ResponseEntity.status(201).build();
    }


    @PostMapping(value = "/schools/attendance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @AuthRequirement(roles = {"admin", "teacher"})
    public ResponseEntity<?> createSchoolAttendance(HttpServletRequest request, @RequestBody SchoolDTO body) {
        assertValid(body.getName(), "name is mandatory");
        assertValid(body.getAddress(), "address is mandatory");
        assertNotNull(body.getClasses(), "classes is mandatory");
        assertNotNull(body.getClasses().getClasses(), "classes is mandatory");

        // POST /api/v1/schools  ---> admin
        // GET /api/v1/schools  ---> admin, teacher
        // GET /api/v1/schools/classes  ---> teacher
        // asdsdsds --> adsadsdsddadss


        schoolService.createSchool(body);

        return ResponseEntity.status(201).build();
    }
}
