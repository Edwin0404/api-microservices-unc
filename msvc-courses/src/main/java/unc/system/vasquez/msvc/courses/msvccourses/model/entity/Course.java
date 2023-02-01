package unc.system.vasquez.msvc.courses.msvccourses.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import unc.system.vasquez.msvc.courses.msvccourses.model.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_user")
    private List<CourseUser> courseUserList;
    @Transient
    private List<User> userList;

    public Course() {
        courseUserList = new ArrayList<>();
        userList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<CourseUser> getCourseUserList() {
        return courseUserList;
    }
    public void setCourseUserList(List<CourseUser> courseUserList) {
        this.courseUserList = courseUserList;
    }
    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    //METODOS
    public void addCourseUser(CourseUser courseUser) {
        courseUserList.add(courseUser);
    }
    public void removeCourseUser(CourseUser courseUser) {
        courseUserList.remove(courseUser);
    }
}
