package augusto108.ces.studenttracker.assemblers;

import augusto108.ces.studenttracker.controllers.StudentController;
import augusto108.ces.studenttracker.entities.Student;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentEntityModelAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {
    @Override
    public EntityModel<Student> toModel(Student entity) {
        final Class<StudentController> c = StudentController.class;

        return EntityModel.of(
                entity,
                linkTo(methodOn(c).getStudent(entity.getId(), new HashMap<>())).withSelfRel(),
                linkTo(methodOn(c).updateStudent(entity.getId(), new Student(), new HashMap<>())).withRel("update"),
                linkTo(methodOn(c).deleteStudent(entity.getId(), new HashMap<>())).withRel("delete"),
                linkTo(methodOn(c).getStudents(new HashMap<>())).withRel("students")
        );
    }

    @Override
    public CollectionModel<EntityModel<Student>> toCollectionModel(Iterable<? extends Student> entities) {
        final Class<StudentController> c = StudentController.class;

        List<EntityModel<Student>> studentEntityModels = new ArrayList<>();

        for (Student entity : entities) {
            studentEntityModels.add(toModel(entity));
        }

        return CollectionModel.of(
                studentEntityModels,
                linkTo(methodOn(c).getStudents(new HashMap<>())).withSelfRel(),
                linkTo(methodOn(c).searchStudents("", new HashMap<>())).withRel("search"),
                linkTo(methodOn(c).saveStudent(new Student(), new HashMap<>())).withRel("save")
        );
    }
}
