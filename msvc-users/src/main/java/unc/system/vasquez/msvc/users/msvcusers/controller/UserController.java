package unc.system.vasquez.msvc.users.msvcusers.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import unc.system.vasquez.msvc.users.msvcusers.entity.User;
import unc.system.vasquez.msvc.users.msvcusers.service.UserService;

import java.util.*;

@RestController     //Classe es una api rest, se comunica por sus metos con la base de datos
@RequestMapping("/api/users") ///direccion de url
public class UserController {

    @Autowired  //extrae los metodos del CrudRepository para poder usarlos
    private UserService userService;

    //CONSULTAS DE USUARIO
    @GetMapping //GET = estrae datos de la base de datos, no resive datos
    public List<User> list() {
        return userService.getAll();
    }
    @GetMapping("/{id}") //Resive datos
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Optional<User> optional = userService.getById(id);
        if (optional.isPresent()) {     //isPresent si contiene datos
            return ResponseEntity.ok(optional.get());
        } else
            return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        if (userService.getByEmail(user.getEmail()).isPresent())
            return ResponseEntity.badRequest().body(Collections.singletonMap("Memsaje", "Ya exsiste un usuario con ese email"));
        if (result.hasErrors())
            return validate(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));  //Estados optimo de una appWeb es 200 o 201
    }
    @PutMapping("/{id}")    //PUT = actualiza la bases de datos
    public ResponseEntity<?> edit(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) { //@Valid = l¿valida la informacion
        if (result.hasErrors())
            return validate(result);
        Optional<User> optional = userService.getById(id); //Verifica que la clase exista
        if (optional.isPresent()) {
            User userDB = optional.get();
            if (!user.getEmail().isBlank() && !user.getEmail().equalsIgnoreCase(userDB.getEmail()) &&
                    userService.getByEmail(user.getEmail()).isPresent())
                return ResponseEntity.badRequest().body(Collections.singletonMap("Memsaje", "Ya exist un usurious con ese email"));
            userDB.setName(user.getName());
            userDB.setEmail(user.getEmail());
            userDB.setPassword(user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDB));
        } else
            return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")  //DELETE = Elimina de la base de datos
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> optional = userService.getById(id); //Verifica que la clase exista
        if (optional.isPresent()) {
            userService.delete(id);
            return ResponseEntity.noContent().build();  //Indicar que se aya borrado
        } else
            return ResponseEntity.notFound().build();
    }

    //FUNCIONES PRIVADAS
    private static ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
