package unc.system.vasquez.msvc.courses.msvccourses.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import unc.system.vasquez.msvc.courses.msvccourses.model.User;

import java.util.List;

@FeignClient(name = "msvc-users", url = "http://localhost:8001/api/users")
public interface UserClientRest {
    @GetMapping("/{id}")
    User detail(@PathVariable Long id);
    @PostMapping
    User create(@RequestBody User user);
    @GetMapping("/users-by-course")
    List<User> usersByCourse(@RequestParam Iterable<Long> ids);
}
