package augusto108.ces.studenttracker.repositories;

import augusto108.ces.studenttracker.entities.UndergraduateProgram;

import java.util.List;

public interface UndergraduateProgramRepository {
    UndergraduateProgram getUndergraduateProgram(Long id);

    List<UndergraduateProgram> getUndergraduatePrograms();

    UndergraduateProgram saveUndergraduateProgram(UndergraduateProgram undergraduateProgram);

    UndergraduateProgram updateUndergraduateProgram(UndergraduateProgram undergraduateProgram);

    void deleteUndergraduateProgram(UndergraduateProgram undergraduateProgram);
}
