package augusto108.ces.studenttracker.controllers;

import augusto108.ces.studenttracker.entities.UndergraduateProgram;
import augusto108.ces.studenttracker.services.UndergraduateProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/undergraduate-programs")
public class UndergraduateProgramController {
    private final UndergraduateProgramService undergraduateProgramService;

    public UndergraduateProgramController(UndergraduateProgramService undergraduateProgramService) {
        this.undergraduateProgramService = undergraduateProgramService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UndergraduateProgram> getUndergraduatePrograms(@PathVariable Long id) {
        return ResponseEntity.ok(undergraduateProgramService.getUndergraduateProgram(id));
    }

    @GetMapping(value = {"/", ""}, produces = "application/json")
    public ResponseEntity<List<UndergraduateProgram>> getUndergraduatePrograms() {
        return ResponseEntity.ok(undergraduateProgramService.getUndergraduatePrograms());
    }
}
