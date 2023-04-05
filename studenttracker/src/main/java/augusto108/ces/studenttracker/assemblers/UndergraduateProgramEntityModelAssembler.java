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
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UndergraduateProgramEntityModelAssembler
        implements RepresentationModelAssembler<UndergraduateProgram, EntityModel<UndergraduateProgram>> {
    private final Class<UndergraduateProgramController> c = UndergraduateProgramController.class;
    private final Map<String, String> map = new HashMap<>();

    @Override
    public EntityModel<UndergraduateProgram> toModel(UndergraduateProgram entity) {
        int maxResultsValue = 5;
        int pageValue = 0;

        return EntityModel.of(
                entity,
                linkTo(methodOn(c).getUndergraduateProgram(entity.getId(), map)).withSelfRel(),
                linkTo(methodOn(c)
                        .updateUndergraduateProgram(entity.getId(), new UndergraduateProgram(), map))
                        .withRel("update"),
                linkTo(methodOn(c).deleteUndergraduateProgram(entity.getId(), map)).withRel("delete"),
                linkTo(methodOn(c).getUndergraduatePrograms(pageValue, maxResultsValue, map))
                        .withRel("undergraduatePrograms")
        );
    }

    public CollectionModel<EntityModel<UndergraduateProgram>> toCollectionModel(
            Iterable<? extends UndergraduateProgram> entities, int pageValue, int maxResults
    ) {
        List<EntityModel<UndergraduateProgram>> undergraduateProgramEntityModels = new ArrayList<>();

        for (UndergraduateProgram entity : entities) {
            undergraduateProgramEntityModels.add(toModel(entity));
        }

        return CollectionModel.of(
                undergraduateProgramEntityModels,
                linkTo(methodOn(c).getUndergraduatePrograms(pageValue, maxResults, map)).withSelfRel(),
                linkTo(methodOn(c).searchUndergraduatePrograms("", map)).withRel("search"),
                linkTo(methodOn(c).saveUndergraduateProgram(new UndergraduateProgram(), map)).withRel("save")
        );
    }
}
