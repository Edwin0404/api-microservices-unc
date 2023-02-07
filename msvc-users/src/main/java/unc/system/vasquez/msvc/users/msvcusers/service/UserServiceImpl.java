package unc.system.vasquez.msvc.users.msvcusers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unc.system.vasquez.msvc.users.msvcusers.client.CourseClientRest;
import unc.system.vasquez.msvc.users.msvcusers.entity.User;
import unc.system.vasquez.msvc.users.msvcusers.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    //extrae los metodos del CrudRepository para poder usarlos //Permite jalar elementos de otro espacio para trabajar con sus metodos aqui
    private UserRepository userRepository;
    @Autowired
    private CourseClientRest courseClientRest;

    @Override
    @Transactional  //Sola guardara
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional  //Sola guardara
    public void delete(Long id) {
        userRepository.deleteById(id);
        courseClientRest.deleteCourseUserById(id);
    }

    @Override
    @Transactional(readOnly = true) //Transaccion de un repositorio a una app - lee datos
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true) //Transaccion de un repositorio a una app - lee datos
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listById(Iterable<Long> ids) {
        return (List<User>) userRepository.findAllById(ids);
    }

}
