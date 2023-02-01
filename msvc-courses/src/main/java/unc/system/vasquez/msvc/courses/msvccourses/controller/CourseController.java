package unc.system.vasquez.msvc.courses.msvccourses.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import unc.system.vasquez.msvc.courses.msvccourses.model.entity.Course;
import unc.system.vasquez.msvc.courses.msvccourses.service.CourseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping //GET = estrae datos de la base de datos, no resive datos
    public ResponseEntity<List<Course>> list() {
        return ResponseEntity.ok(courseService.getAll());
    }
    @GetMapping("/{id}") //Resive datos
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Optional<Course> optional = courseService.getById(id);
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return ResponseEntity.notFound().build();
    }
    @PostMapping //POST = Crea en la base de datos
    public ResponseEntity<?> create(@Valid @RequestBody Course course, BindingResult result) {
        //Course courseDB = courseService.save(course);
        //return ResponseEntity.status(HttpStatus.CREATED).body(courseDB); //Estados optimo de una appWeb es 200 o 201
        if (result.hasErrors())
            return validate(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody Course course, BindingResult result, @PathVariable Long id) {
        Optional<Course> optional = courseService.getById(id);
        if (optional.isPresent()) {
            Course courseDB = optional.get();
            if (result.hasErrors())
                return validate(result);
            courseDB.setName((course.getName()));
            return  ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseDB));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Course> optional = courseService.getById(id);//Verifica que la clase exista
        if (optional.isPresent()){
            courseService.delete(optional.get().getId());
            return ResponseEntity.noContent().build();//Indicar que se aya borrado
        }
        return ResponseEntity.notFound().build();
    }

    private static  ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage()));
        return  ResponseEntity.badRequest().body(errors);
    }
}
