package unc.system.vasquez.msvc.courses.msvccourses.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import unc.system.vasquez.msvc.courses.msvccourses.model.User;

//@FeignClient(name = "msvc-users")
@FeignClient(name = "msvc-users", url = "http://localhost:8001/api/users")
public interface UserClientRest {
    //@RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @GetMapping("/{id}")
    User detail(@PathVariable Long id);
    //@RequestMapping(method = RequestMethod.POST, value = "/", consumes = "application/json")
    @PostMapping
    User create(@RequestBody User user);
}
