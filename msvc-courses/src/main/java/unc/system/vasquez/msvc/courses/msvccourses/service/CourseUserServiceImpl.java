package unc.system.vasquez.msvc.courses.msvccourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unc.system.vasquez.msvc.courses.msvccourses.model.entity.CourseUser;
import unc.system.vasquez.msvc.courses.msvccourses.repository.CourseUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseUserServiceImpl implements CourseUserService{

    @Autowired
    private CourseUserRepository courseUserRepository;
    @Override
    public CourseUser save(CourseUser courseUser) {
        return courseUserRepository.save(courseUser);
    }

    @Override
    public void delete(Long id) {
        courseUserRepository.deleteById(id);
    }

    @Override
    public List<CourseUser> getAll() {
        return (List<CourseUser>) courseUserRepository.findAll();
    }

    @Override
    public Optional<CourseUser> getById(Long id) {
        return courseUserRepository.findById(id);
    }
}
