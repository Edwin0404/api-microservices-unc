package unc.system.vasquez.msvc.courses.msvccourses.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import unc.system.vasquez.msvc.courses.msvccourses.model.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
    @Modifying
    @Query("DELETE FROM CourseUser value WHERE value.idUser=?1")
    void deleteCourseUserById(Long id);
}
