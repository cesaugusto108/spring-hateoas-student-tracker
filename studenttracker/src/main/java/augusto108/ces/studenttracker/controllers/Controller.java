package augusto108.ces.studenttracker.controllers;

import augusto108.ces.studenttracker.controllers.helpers.DefaultParameterObj;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = {"/", ""})
public class Controller {
    private final DefaultParameterObj param = new DefaultParameterObj();
    private final int page = param.getPage();
    private final int max = param.getMaxResults();
    private final Map<String, String> map = param.getMap();

    @GetMapping
    public ResponseEntity<List<Link>> getAppAggregateRoots() {
        Link addressesLink = linkTo(methodOn(AddressController.class).getAddresses(page, max, map)).withRel("addresses");
        Link campusesLink = linkTo(methodOn(CampusController.class).getCampuses(page, max, map)).withRel("campuses");
        Link studentsLink = linkTo(methodOn(StudentController.class).getStudents(page, max, map)).withRel("students");
        Link undergraduateProgramsLink = linkTo(methodOn(UndergraduateProgramController.class).getUndergraduatePrograms(page, max, map))
                .withRel("undergraduatePrograms");

        return ResponseEntity.ok(Arrays.asList(addressesLink, campusesLink, studentsLink, undergraduateProgramsLink));
    }
}
