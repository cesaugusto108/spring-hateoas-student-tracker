package augusto108.ces.studenttracker.controllers;

import augusto108.ces.studenttracker.assemblers.CampusEntityModelAssembler;
import augusto108.ces.studenttracker.entities.Campus;
import augusto108.ces.studenttracker.services.CampusService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/campuses")
public class CampusController {
    private static final Logger LOGGER = Logger.getLogger(CampusController.class.getName());

    private final CampusService campusService;
    private final CampusEntityModelAssembler assembler;

    public CampusController(CampusService campusService, CampusEntityModelAssembler assembler) {
        this.campusService = campusService;
        this.assembler = assembler;
    }

    private static void log(Map<String, String> requestHeadersMap) {
        requestHeadersMap
                .forEach((k, v) -> LOGGER.info(String.format("Request header: %s = %s", k, v)));
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<Campus>> getCampus(
            @PathVariable("id") Long id,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        try {
            return ResponseEntity.ok(assembler.toModel(campusService.getCampus(id)));
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        }
    }

    @GetMapping(value = {"/", ""}, produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<Campus>>> getCampuses(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "maxResults", defaultValue = "5", required = false) int maxResults,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity
                .ok(assembler.toCollectionModel(campusService.getCampuses(page, maxResults), page, maxResults));
    }

    @PostMapping(value = "/save", produces = "application/hal+json", consumes = "application/json")
    public ResponseEntity<EntityModel<Campus>> saveCampus(
            @RequestBody Campus campus,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(campusService.saveCampus(campus)));
    }

    @PutMapping(value = "/{id}/update", produces = "application/hal+json")
    public ResponseEntity<EntityModel<Campus>> updateCampus(
            @PathVariable("id") Long id,
            @RequestBody Campus campus,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        Campus c = campusService.getCampus(id);

        c.setDescription(campus.getDescription());

        return ResponseEntity.ok(assembler.toModel(campusService.updateCampus(c)));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCampus(
            @PathVariable("id") Long id,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        try {
            campusService.deleteCampus(campusService.getCampus(id));
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<Campus>>> searchCampuses(
            @RequestParam String search,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity.ok(assembler.toCollectionModel(campusService.searchCampus(search)));
    }
}
