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
    public List<UndergraduateProgram> getUndergraduatePrograms(int pageValue, int maxResults) {
        return undergraduateProgramRepository.getUndergraduatePrograms(pageValue, maxResults);
    }

    @Override
    public UndergraduateProgram saveUndergraduateProgram(UndergraduateProgram undergraduateProgram) {
        return undergraduateProgramRepository.saveUndergraduateProgram(undergraduateProgram);
    }

    @Override
    public UndergraduateProgram updateUndergraduateProgram(UndergraduateProgram undergraduateProgram) {
        return undergraduateProgramRepository.updateUndergraduateProgram(undergraduateProgram);
    }

    @Override
    public void deleteUndergraduateProgram(UndergraduateProgram undergraduateProgram) {
        undergraduateProgramRepository.deleteUndergraduateProgram(undergraduateProgram);
    }

    @Override
    public List<UndergraduateProgram> searchUndergraduatePrograms(String search) {
        return undergraduateProgramRepository.searchUndergraduatePrograms(search);
    }
}
