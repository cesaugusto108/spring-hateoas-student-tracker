package augusto108.ces.studenttracker.assemblers;

import augusto108.ces.studenttracker.controllers.AddressController;
import augusto108.ces.studenttracker.controllers.StudentController;
import augusto108.ces.studenttracker.entities.Address;
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
public class AddressEntityModelAssembler implements RepresentationModelAssembler<Address, EntityModel<Address>> {
    private final Class<AddressController> c = AddressController.class;
    private final Map<String, String> map = new HashMap<>();

    @Override
    public EntityModel<Address> toModel(Address entity) {
        int maxResultsValue = 5;
        int pageValue = 0;

        return EntityModel.of(
                entity,
                linkTo(methodOn(c).getAddress(entity.getId(), map)).withSelfRel(),
                linkTo(methodOn(c).updateAddress(entity.getId(), new Address(), map)).withRel("update"),
                linkTo(methodOn(c).deleteAddress(entity.getId(), map)).withRel("delete"),
                linkTo(methodOn(c).getAddresses(pageValue, maxResultsValue, map)).withRel("addresses")
        );
    }

    public CollectionModel<EntityModel<Address>> toCollectionModel(
            Iterable<? extends Address> entities, int pageValue, int maxResults
    ) {
        List<EntityModel<Address>> addressEntityModels = new ArrayList<>();

        for (Address entity : entities) {
            addressEntityModels.add(toModel(entity));
        }

        return CollectionModel.of(
                addressEntityModels,
                linkTo(methodOn(c).getAddresses(pageValue, maxResults, map)).withSelfRel(),
                linkTo(methodOn(c).searchAddresses("", map)).withRel("search"),
                linkTo(methodOn(c).saveAddress(new Address(), map)).withRel("save")
        );
    }
}
