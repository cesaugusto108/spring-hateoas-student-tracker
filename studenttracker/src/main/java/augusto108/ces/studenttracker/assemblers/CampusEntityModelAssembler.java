package augusto108.ces.studenttracker.assemblers;

import augusto108.ces.studenttracker.controllers.CampusController;
import augusto108.ces.studenttracker.entities.Campus;
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
public class CampusEntityModelAssembler implements RepresentationModelAssembler<Campus, EntityModel<Campus>> {
    @Override
    public EntityModel<Campus> toModel(Campus entity) {
        final Class<CampusController> c = CampusController.class;

        return EntityModel.of(
                entity,
                linkTo(methodOn(c).getCampus(entity.getId(), new HashMap<>())).withSelfRel(),
                linkTo(methodOn(c).updateCampus(entity.getId(), new Campus(), new HashMap<>())).withRel("update"),
                linkTo(methodOn(c).deleteCampus(entity.getId(), new HashMap<>())).withRel("delete"),
                linkTo(methodOn(c).getCampuses(new HashMap<>())).withRel("campuses")
        );
    }

    @Override
    public CollectionModel<EntityModel<Campus>> toCollectionModel(Iterable<? extends Campus> entities) {
        final Class<CampusController> c = CampusController.class;

        List<EntityModel<Campus>> campusEntityModels = new ArrayList<>();

        for (Campus entity : entities) {
            campusEntityModels.add(toModel(entity));
        }

        return CollectionModel.of(
                campusEntityModels,
                linkTo(methodOn(c).getCampuses(new HashMap<>())).withSelfRel(),
                linkTo(methodOn(c).searchCampuses("", new HashMap<>())).withRel("search"),
                linkTo(methodOn(c).saveCampus(new Campus(), new HashMap<>())).withRel("save")
        );
    }
}
