package unc.system.vasquez.msvc.users.msvcusers.service;

import unc.system.vasquez.msvc.users.msvcusers.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    void delete(Long id);
    List<User> getAll();
    Optional<User> getById(Long id);
    Optional<User> getByEmail(String email);
    List<User> listById(Iterable<Long> ids);
}
