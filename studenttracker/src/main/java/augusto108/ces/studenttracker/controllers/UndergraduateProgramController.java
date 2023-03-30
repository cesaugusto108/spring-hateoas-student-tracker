package augusto108.ces.studenttracker.controllers;

import augusto108.ces.studenttracker.assemblers.UndergraduateProgramEntityModelAssembler;
import augusto108.ces.studenttracker.entities.UndergraduateProgram;
import augusto108.ces.studenttracker.services.UndergraduateProgramService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @PathVariable Long id, @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity.ok(assembler.toModel(undergraduateProgramService.getUndergraduateProgram(id)));
    }

    @GetMapping(value = {"/", ""}, produces = "application/hal+json")
    public ResponseEntity<CollectionModel<EntityModel<UndergraduateProgram>>> getUndergraduatePrograms(
            @RequestHeader Map<String, String> requestHeadersMap
    ) {
        log(requestHeadersMap);

        return ResponseEntity.ok(assembler.toCollectionModel(undergraduateProgramService.getUndergraduatePrograms()));
    }
}
