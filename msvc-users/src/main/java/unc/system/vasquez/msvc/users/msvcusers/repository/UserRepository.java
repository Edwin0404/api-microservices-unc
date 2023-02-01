package unc.system.vasquez.msvc.users.msvcusers.repository;

import org.springframework.data.repository.CrudRepository;
import unc.system.vasquez.msvc.users.msvcusers.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
