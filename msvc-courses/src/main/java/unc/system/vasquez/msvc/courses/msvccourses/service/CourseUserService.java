package unc.system.vasquez.msvc.courses.msvccourses.service;

import unc.system.vasquez.msvc.courses.msvccourses.model.entity.CourseUser;

import java.util.List;
import java.util.Optional;

public interface CourseUserService {
    CourseUser save(CourseUser courseUser);
    void delete(Long id);
    List<CourseUser> getAll();
    Optional<CourseUser> getById(Long id);
}
