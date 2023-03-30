package augusto108.ces.studenttracker.services;

import augusto108.ces.studenttracker.entities.UndergraduateProgram;

import java.util.List;

public interface UndergraduateProgramService {
    UndergraduateProgram getUndergraduateProgram(Long id);

    List<UndergraduateProgram> getUndergraduatePrograms();

    UndergraduateProgram saveUndergraduateProgram(UndergraduateProgram undergraduateProgram);

    UndergraduateProgram updateUndergraduateProgram(UndergraduateProgram undergraduateProgram);

    void deleteUndergraduateProgram(UndergraduateProgram undergraduateProgram);
}
