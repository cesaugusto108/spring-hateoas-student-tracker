package augusto108.ces.studenttracker.controllers;

import augusto108.ces.studenttracker.assemblers.StudentEntityModelAssembler;
import augusto108.ces.studenttracker.entities.Student;
import augusto108.ces.studenttracker.services.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/students")
public class StudentController {
    private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());

    private final StudentService studentService;
    private final StudentEntityModelAssembler assembler;

    public StudentController(StudentService studentService, StudentEntityModelAssembler assembler) {
        this.studentService = studentService;
        this.assembler = assembler;
    }

    private static void log(Map<String, String> requestHeadersMap) {
        requestHeadersMap
                .forEach((k, v) -> LOGGER.info(String.format("Request header: %s = %s", k, v)));
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<Student>> getStudent(
            @PathVariable("id") Long id, @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        try {
            return ResponseEntity.ok(assembler.toModel(studentService.getStudent(id)));
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    @GetMapping(value = {"/", ""}, produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<Student>>> getStudents(
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity.ok(assembler.toCollectionModel(studentService.getStudents()));
    }

    @PostMapping(value = {"/save"}, produces = "application/hal+json", consumes = "application/json")
    public ResponseEntity<EntityModel<Student>> saveStudent(
            @RequestBody Student student, @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(studentService.saveStudent(student)));
    }
}
