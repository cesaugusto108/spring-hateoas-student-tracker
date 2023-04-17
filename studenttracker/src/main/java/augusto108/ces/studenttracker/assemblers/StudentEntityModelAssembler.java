package augusto108.ces.studenttracker.assemblers;

import augusto108.ces.studenttracker.controllers.StudentController;
import augusto108.ces.studenttracker.controllers.helpers.DefaultParameterObj;
import augusto108.ces.studenttracker.entities.Student;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentEntityModelAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {
    private final Class<StudentController> c = StudentController.class;
    private final DefaultParameterObj param = new DefaultParameterObj();
    private final int page = param.getPage();
    private final int max = param.getMaxResults();
    private final Map<String, String> map = param.getMap();

    @Override
    public EntityModel<Student> toModel(Student entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(c).getStudent(entity.getId(), map)).withSelfRel(),
                linkTo(methodOn(c).updateStudent(entity.getId(), new Student(), map)).withRel("update"),
                linkTo(methodOn(c).deleteStudent(entity.getId(), map)).withRel("delete"),
                linkTo(methodOn(c).getStudents(page, max, map)).withRel("students")
        );
    }

    public CollectionModel<EntityModel<Student>> toCollectionModel(
            Iterable<? extends Student> entities, int pageValue, int maxResults
    ) {
        List<EntityModel<Student>> studentEntityModels = new ArrayList<>();

        for (Student entity : entities) {
            studentEntityModels.add(toModel(entity));
        }

        return CollectionModel.of(
                studentEntityModels,
                linkTo(methodOn(c).getStudents(pageValue, maxResults, map)).withSelfRel(),
                linkTo(methodOn(c).searchStudents("", map)).withRel("search"),
                linkTo(methodOn(c).saveStudent(new Student(), map)).withRel("save")
        );
    }
}
