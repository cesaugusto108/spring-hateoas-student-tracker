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
            @PathVariable("id") Long id,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        try {
            return ResponseEntity.ok(assembler.toModel(studentService.getStudent(id)));
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        }
    }

    @GetMapping(value = {"/", ""}, produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<Student>>> getStudents(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "max", defaultValue = "5", required = false) int max,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity
                .ok(assembler.toCollectionModel(studentService.getStudents(page, max), page, max));
    }

    @PostMapping(value = "/save", produces = "application/hal+json", consumes = "application/json")
    public ResponseEntity<EntityModel<Student>> saveStudent(
            @RequestBody Student student,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(studentService.saveStudent(student)));
    }

    @PutMapping(value = "/{id}/update", produces = "application/hal+json", consumes = "application/json")
    public ResponseEntity<EntityModel<Student>> updateStudent(
            @PathVariable("id") Long id,
            @RequestBody Student student,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        Student s;

        try {
            s = studentService.getStudent(id);
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        }

        s.setName(student.getName());
        s.setEmail(student.getEmail());
        s.setRegistration(student.getRegistration());
        s.setAddresses(student.getAddresses());
        s.setUndergraduatePrograms(student.getUndergraduatePrograms());

        return ResponseEntity.status(HttpStatus.OK).body(assembler.toModel(studentService.updateStudent(s)));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable("id") Long id,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        try {
            studentService.deleteStudent(studentService.getStudent(id));
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<Student>>> searchStudents(
            @RequestParam String search,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity.ok(assembler.toCollectionModel(studentService.searchStudents(search)));
    }
}
