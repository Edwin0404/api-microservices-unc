package unc.system.vasquez.msvc.courses.msvccourses.service;

import unc.system.vasquez.msvc.courses.msvccourses.model.User;
import unc.system.vasquez.msvc.courses.msvccourses.model.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course save(Course course);
    void delete(Long id);
    List<Course> getAll();
    Optional<Course> getById(Long id);

    //Metodos remotos relacionados al cliente Http (a ApiRest, comunicacion con el otro mscv)
    Optional<User> assignUser(User user, Long idCourse);
    Optional<User> createUser(User user, Long idCourse);
    Optional<User> removeUser(User user, Long idCourse);
    Optional<Course> usersById(Long id);
    void deleteCourseUserById(Long id);
}
