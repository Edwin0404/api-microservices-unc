package unc.system.vasquez.msvc.courses.msvccourses.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Courses_Users")
public class CourseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_user", unique = true)
    private Long idUser;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdUser() {
        return idUser;
    }
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof CourseUser courseUser))
            return false;
        return idUser != null && idUser.equals(courseUser.idUser);
    }
}
