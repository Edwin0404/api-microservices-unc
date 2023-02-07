package unc.system.vasquez.msvc.courses.msvccourses.repository;

import org.springframework.data.repository.CrudRepository;
import unc.system.vasquez.msvc.courses.msvccourses.model.entity.CourseUser;

import java.util.Optional;

public interface CourseUserRepository extends CrudRepository<CourseUser, Long> {
    Optional<CourseUser> findByIdUser(Long idUser);
}
