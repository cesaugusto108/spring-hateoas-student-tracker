package augusto108.ces.studenttracker.controllers;

import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = {"/", ""})
public class Controller {
    @GetMapping
    public ResponseEntity<List<Link>> getAppAggregateRoots() throws NoSuchMethodException {
        Link addressesLink = linkTo(methodOn(AddressController.class).getAddresses(0, 5, new HashMap<>())).withRel("addresses");
        Link campusesLink = linkTo(methodOn(CampusController.class).getCampuses(0, 5, new HashMap<>())).withRel("campuses");
        Link studentsLink = linkTo(methodOn(StudentController.class).getStudents(0, 5, new HashMap<>())).withRel("students");
        Link undergraduateProgramsLink = linkTo(methodOn(UndergraduateProgramController.class).getUndergraduatePrograms(0, 5, new HashMap<>()))
                .withRel("undergraduatePrograms");

        return ResponseEntity.ok(Arrays.asList(addressesLink, campusesLink, studentsLink, undergraduateProgramsLink));
    }
}
