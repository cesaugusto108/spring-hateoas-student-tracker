package augusto108.ces.studenttracker.assemblers;

import augusto108.ces.studenttracker.controllers.UndergraduateProgramController;
import augusto108.ces.studenttracker.entities.UndergraduateProgram;
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
public class UndergraduateProgramEntityModelAssembler
        implements RepresentationModelAssembler<UndergraduateProgram, EntityModel<UndergraduateProgram>> {
    @Override
    public EntityModel<UndergraduateProgram> toModel(UndergraduateProgram entity) {
        final Class<UndergraduateProgramController> c = UndergraduateProgramController.class;

        return EntityModel.of(
                entity,
                linkTo(methodOn(c).getUndergraduateProgram(entity.getId(), new HashMap<>())).withSelfRel(),
                linkTo(methodOn(c)
                        .updateUndergraduateProgram(entity.getId(), new UndergraduateProgram(), new HashMap<>()))
                        .withRel("update"),
                linkTo(methodOn(c).deleteUndergraduateProgram(entity.getId(), new HashMap<>())).withRel("delete"),
                linkTo(methodOn(c).getUndergraduatePrograms(new HashMap<>())).withRel("undergraduatePrograms")
        );
    }

    @Override
    public CollectionModel<EntityModel<UndergraduateProgram>> toCollectionModel(
            Iterable<? extends UndergraduateProgram> entities
    ) {
        final Class<UndergraduateProgramController> c = UndergraduateProgramController.class;

        List<EntityModel<UndergraduateProgram>> undergraduateProgramEntityModels = new ArrayList<>();

        for (UndergraduateProgram entity : entities) {
            undergraduateProgramEntityModels.add(toModel(entity));
        }

        return CollectionModel.of(
                undergraduateProgramEntityModels,
                linkTo(methodOn(c).getUndergraduatePrograms(new HashMap<>())).withSelfRel(),
                linkTo(methodOn(c).saveUndergraduateProgram(new UndergraduateProgram(), new HashMap<>())).withRel("save")
        );
    }
}
