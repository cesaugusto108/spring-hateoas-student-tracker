package augusto108.ces.studenttracker.assemblers;

import augusto108.ces.studenttracker.controllers.AddressController;
import augusto108.ces.studenttracker.entities.Address;
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
public class AddressEntityModelAssembler implements RepresentationModelAssembler<Address, EntityModel<Address>> {
    @Override
    public EntityModel<Address> toModel(Address entity) {
        final Class<AddressController> c = AddressController.class;

        return EntityModel.of(
                entity,
                linkTo(methodOn(c).getAddress(entity.getId(), new HashMap<>())).withSelfRel(),
                linkTo(methodOn(c).updateAddress(entity.getId(), new Address(), new HashMap<>())).withRel("update"),
                linkTo(methodOn(c).deleteAddress(entity.getId(), new HashMap<>())).withRel("delete"),
                linkTo(methodOn(c).getAddresses(new HashMap<>())).withRel("addresses")
        );
    }

    @Override
    public CollectionModel<EntityModel<Address>> toCollectionModel(Iterable<? extends Address> entities) {
        final Class<AddressController> c = AddressController.class;

        List<EntityModel<Address>> addressEntityModels = new ArrayList<>();

        for (Address entity : entities) {
            addressEntityModels.add(toModel(entity));
        }

        return CollectionModel.of(
                addressEntityModels,
                linkTo(methodOn(c).getAddresses(new HashMap<>())).withSelfRel(),
                linkTo(methodOn(c).searchAddresses("", new HashMap<>())).withRel("search"),
                linkTo(methodOn(c).saveAddress(new Address(), new HashMap<>())).withRel("save")
        );
    }
}
