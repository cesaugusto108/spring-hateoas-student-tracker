package augusto108.ces.studenttracker.controllers;

import augusto108.ces.studenttracker.assemblers.UndergraduateProgramEntityModelAssembler;
import augusto108.ces.studenttracker.entities.UndergraduateProgram;
import augusto108.ces.studenttracker.services.UndergraduateProgramService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/undergraduate-programs")
public class UndergraduateProgramController {
    private static final Logger LOGGER = Logger.getLogger(UndergraduateProgramController.class.getName());

    private final UndergraduateProgramService undergraduateProgramService;
    private final UndergraduateProgramEntityModelAssembler assembler;

    public UndergraduateProgramController(
            UndergraduateProgramService undergraduateProgramService, UndergraduateProgramEntityModelAssembler assembler
    ) {
        this.undergraduateProgramService = undergraduateProgramService;
        this.assembler = assembler;
    }

    private static void log(Map<String, String> requestHeadersMap) {
        requestHeadersMap
                .forEach((k, v) -> LOGGER.info(String.format("Request header: %s = %s", k, v)));
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<UndergraduateProgram>> getUndergraduateProgram(
            @PathVariable("id") Long id,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        try {
            return ResponseEntity.ok(assembler.toModel(undergraduateProgramService.getUndergraduateProgram(id)));
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    @GetMapping(value = {"/", ""}, produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<UndergraduateProgram>>> getUndergraduatePrograms(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "maxResults", defaultValue = "5", required = false) int maxResults,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity
                .ok(
                        assembler.toCollectionModel(
                                undergraduateProgramService.getUndergraduatePrograms(page, maxResults), page, maxResults)
                );
    }

    @PostMapping(value = "/save", produces = "application/hal+json", consumes = "application/json")
    public ResponseEntity<EntityModel<UndergraduateProgram>> saveUndergraduateProgram(
            @RequestBody UndergraduateProgram undergraduateProgram,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(undergraduateProgramService.saveUndergraduateProgram(undergraduateProgram)));
    }

    @PutMapping(value = "/{id}/update", produces = "application/hal+json", consumes = "application/json")
    public ResponseEntity<EntityModel<UndergraduateProgram>> updateUndergraduateProgram(
            @PathVariable("id") Long id,
            @RequestBody UndergraduateProgram undergraduateProgram,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        UndergraduateProgram u;

        try {
            u = undergraduateProgramService.getUndergraduateProgram(id);
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }

        u.setDescription(undergraduateProgram.getDescription());
        u.setCampus(undergraduateProgram.getCampus());
        u.setStudents(undergraduateProgram.getStudents());

        return ResponseEntity.ok(assembler.toModel(undergraduateProgramService.updateUndergraduateProgram(u)));
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Void> deleteUndergraduateProgram(
            @PathVariable("id") Long id,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        try {
            undergraduateProgramService
                    .deleteUndergraduateProgram(undergraduateProgramService.getUndergraduateProgram(id));
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage() + ". Id: " + id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<UndergraduateProgram>>> searchUndergraduatePrograms(
            @RequestParam String search,
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity
                .ok(assembler.toCollectionModel(undergraduateProgramService.searchUndergraduatePrograms(search)));
    }
}
