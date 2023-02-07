package unc.system.vasquez.msvc.users.msvcusers.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-courses", url = "http://localhost:8002/api/courses")
public interface CourseClientRest {
    @DeleteMapping("/delete-course-user/{id}")
    void deleteCourseUserById(@PathVariable Long id);
}
