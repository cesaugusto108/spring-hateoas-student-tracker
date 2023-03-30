package augusto108.ces.studenttracker.services;

import augusto108.ces.studenttracker.entities.UndergraduateProgram;
import augusto108.ces.studenttracker.repositories.UndergraduateProgramRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UndergraduateProgramServiceImpl implements UndergraduateProgramService {
    private final UndergraduateProgramRepository undergraduateProgramRepository;

    public UndergraduateProgramServiceImpl(UndergraduateProgramRepository undergraduateProgramRepository) {
        this.undergraduateProgramRepository = undergraduateProgramRepository;
    }

    @Override
    public UndergraduateProgram getUndergraduateProgram(Long id) {
        return undergraduateProgramRepository.getUndergraduateProgram(id);
    }

    @Override
    public List<UndergraduateProgram> getUndergraduatePrograms() {
        return undergraduateProgramRepository.getUndergraduatePrograms();
    }
}
