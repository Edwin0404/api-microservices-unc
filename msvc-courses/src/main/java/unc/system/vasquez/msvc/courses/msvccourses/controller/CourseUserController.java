package unc.system.vasquez.msvc.courses.msvccourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unc.system.vasquez.msvc.courses.msvccourses.model.entity.CourseUser;
import unc.system.vasquez.msvc.courses.msvccourses.service.CourseUserService;

import java.util.List;

@RestController
@RequestMapping("/api/courses-users")
public class CourseUserController {

    @Autowired
    private CourseUserService courseUserService;
    @GetMapping //GET = estrae datos de la base de datos, no resive datos
    public ResponseEntity<List<CourseUser>> list() {
        return ResponseEntity.ok(courseUserService.getAll());
    }

}
