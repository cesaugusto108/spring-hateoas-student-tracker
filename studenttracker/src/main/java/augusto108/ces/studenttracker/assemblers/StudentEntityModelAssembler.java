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
        return EntityModel.of(
                entity,
                linkTo(methodOn(StudentController.class).getStudent(entity.getId(), new HashMap<>())).withSelfRel(),
                linkTo(methodOn(StudentController.class).getStudents(new HashMap<>())).withRel("students")
        );
    }

    @Override
    public CollectionModel<EntityModel<Student>> toCollectionModel(Iterable<? extends Student> entities) {
        List<EntityModel<Student>> studentEntityModels = new ArrayList<>();

        for (Student entity : entities) {
            studentEntityModels.add(toModel(entity));
        }

        return CollectionModel.of(
                studentEntityModels,
                linkTo(methodOn(StudentController.class).getStudents(new HashMap<>())).withSelfRel()
        );
    }
}
