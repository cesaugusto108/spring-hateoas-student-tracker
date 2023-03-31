package augusto108.ces.studenttracker.controllers;

import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = {"/", ""})
public class Controller {
    @GetMapping
    public ResponseEntity<List<Link>> getAppAggregateRoots() throws NoSuchMethodException {
        Method getAddresses = AddressController.class.getMethod("getAddresses", Map.class);
        Method getCampuses = CampusController.class.getMethod("getCampuses", Map.class);
        Method getStudents = StudentController.class.getMethod("getStudents", Map.class);
        Method getUndergraduatePrograms = UndergraduateProgramController.class
                .getMethod("getUndergraduatePrograms", Map.class);

        Link addressesLink = linkTo(getAddresses).withRel("addresses");
        Link campusesLink = linkTo(getCampuses).withRel("campuses");
        Link studentsLink = linkTo(getStudents).withRel("students");
        Link undergraduateProgramsLink = linkTo(getUndergraduatePrograms).withRel("undergraduatePrograms");

        return ResponseEntity.ok(Arrays.asList(addressesLink, campusesLink, studentsLink, undergraduateProgramsLink));
    }
}
