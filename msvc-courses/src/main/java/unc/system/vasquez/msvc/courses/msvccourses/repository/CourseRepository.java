package unc.system.vasquez.msvc.courses.msvccourses.repository;

import org.springframework.data.repository.CrudRepository;
import unc.system.vasquez.msvc.courses.msvccourses.model.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
