package unc.system.vasquez.msvc.courses.msvccourses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unc.system.vasquez.msvc.courses.msvccourses.client.UserClientRest;
import unc.system.vasquez.msvc.courses.msvccourses.model.User;
import unc.system.vasquez.msvc.courses.msvccourses.model.entity.Course;
import unc.system.vasquez.msvc.courses.msvccourses.model.entity.CourseUser;
import unc.system.vasquez.msvc.courses.msvccourses.repository.CourseRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired //Permite jalar elementos de otro espacio para trabajar con sus metodos aqui
    private CourseRepository courseRepository;
    @Autowired //Permite jalar elementos de otro espacio para trabajar con sus metodos aqui
    private CourseUserService courseUserService;
    @Autowired
    private UserClientRest userClientRest;

    @Override
    @Transactional //cambias a la tabla
    public Course save(Course course) {
        return courseRepository.save(course);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true) //solo lectura
    public List<Course> getAll() {
        return (List<Course>) courseRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Course> getById(Long id) {
        return courseRepository.findById(id);
    }

    //METODOS REMOTOS
    @Override
    public Optional<User> assignUser(User user, Long idCourse) {
        Optional<Course> optional = courseRepository.findById(idCourse);
        if (optional.isPresent()) {
            User userBD = userClientRest.detail(user.getId());

            Course course = optional.get();
            /*CourseUser courseUser = new CourseUser();
            courseUser.setIdUser(userBD.getId());*/
            for (CourseUser value : courseUserService.getAll()) {
                if (value.getIdUser() == userBD.getId() && Objects.equals(value.getIdCourse(), course.getId())) {
                    return Optional.empty();
                }
            }
            CourseUser courseUser = courseUserService.save(new CourseUser(userBD.getId(), course.getId()));

            course.addCourseUser(courseUser);
            courseRepository.save(course);
            return Optional.of(userBD);
        }
        return Optional.empty();
    }
    @Override
    public Optional<User> createUser(User user, Long idCourse) {
        Optional<Course> optional = courseRepository.findById(idCourse);
        if (optional.isPresent()) {
            User userDetail = userClientRest.detail(user.getId());
            Course course = optional.get();

            if (userDetail != null)
                return assignUser(userDetail, course.getId());

            User userBD = userClientRest.create(user);

            CourseUser courseUser = courseUserService.save(new CourseUser(course.getId(), userBD.getId()));
            /*CourseUser courseUser = new CourseUser();
            courseUser.setIdUser(userBD.getId());
            courseUser.setIdCourse(course.getId());*/

            course.addCourseUser(courseUser);
            courseRepository.save(course);
            return Optional.of(userBD);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> removeUser(User user, Long idCourse) {
        Optional<Course> optional = courseRepository.findById(idCourse);
        if (optional.isPresent()) {
            User userBD = userClientRest.detail(user.getId());

            Course course = optional.get();
            List<CourseUser> list = course.getCourseUserList();
            list.forEach(value -> {
                if (Objects.equals(value.getIdCourse(), course.getId()) && value.getIdUser() == userBD.getId()) {
                    courseUserService.delete(value.getId());
                    course.removeCourseUser(value);
                }
            });
            /*CourseUser courseUser = new CourseUser();
            courseUser.setIdUser(userBD.getId());

            course.removeCourseUser(courseUser);*/
            courseRepository.save(course);
            return Optional.of(userBD);
        }
        return Optional.empty();
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Course> usersById(Long id) {
        Optional<Course> optional = courseRepository.findById(id);
        if(optional.isPresent()){
            Course course = optional.get();
            if(!course.getCourseUserList().isEmpty()){
                List<Long> ids = course.getCourseUserList().stream().map(CourseUser::getIdUser)
                        .collect(Collectors.toList());
                List<User> userList = userClientRest.usersByCourse(ids);
                course.setUserList(userList);
            }
            return Optional.of(course);
        }
        return Optional.empty();
    }
    @Override
    @Transactional
    public void deleteCourseUserById(Long id) {
        courseRepository.deleteCourseUserById(id);
    }
}
