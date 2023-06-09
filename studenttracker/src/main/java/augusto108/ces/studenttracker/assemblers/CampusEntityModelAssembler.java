package augusto108.ces.studenttracker.assemblers;

import augusto108.ces.studenttracker.controllers.CampusController;
import augusto108.ces.studenttracker.controllers.helpers.DefaultParameterObj;
import augusto108.ces.studenttracker.entities.Campus;
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
public class CampusEntityModelAssembler implements RepresentationModelAssembler<Campus, EntityModel<Campus>> {
    private final Class<CampusController> c = CampusController.class;
    private final DefaultParameterObj param = new DefaultParameterObj();
    private final int page = param.getPage();
    private final int max = param.getMaxResults();
    private final Map<String, String> map = param.getMap();

    @Override
    public EntityModel<Campus> toModel(Campus entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(c).getCampus(entity.getId(), map)).withSelfRel(),
                linkTo(methodOn(c).updateCampus(entity.getId(), new Campus(), map)).withRel("update"),
                linkTo(methodOn(c).deleteCampus(entity.getId(), map)).withRel("delete"),
                linkTo(methodOn(c).getCampuses(page, max, map)).withRel("campuses")
        );
    }

    public CollectionModel<EntityModel<Campus>> toCollectionModel(
            Iterable<? extends Campus> entities, int pageValue, int maxResults
    ) {
        List<EntityModel<Campus>> campusEntityModels = new ArrayList<>();

        for (Campus entity : entities) {
            campusEntityModels.add(toModel(entity));
        }

        return CollectionModel.of(
                campusEntityModels,
                linkTo(methodOn(c).getCampuses(pageValue, maxResults, map)).withSelfRel(),
                linkTo(methodOn(c).searchCampuses("", map)).withRel("search"),
                linkTo(methodOn(c).saveCampus(new Campus(), map)).withRel("save")
        );
    }
}
