package augusto108.ces.studenttracker.services;

import augusto108.ces.studenttracker.entities.Campus;
import augusto108.ces.studenttracker.repositories.CampusRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CampusServiceImpl implements CampusService {
    private final CampusRepository campusRepository;

    public CampusServiceImpl(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    @Override
    public Campus getCampus(Long id) {
        return campusRepository.getCampus(id);
    }

    @Override
    public List<Campus> getCampuses(int pageValue, int maxResults) {
        return campusRepository.getCampuses(pageValue, maxResults);
    }

    @Override
    public Campus saveCampus(Campus campus) {
        return campusRepository.saveCampus(campus);
    }

    @Override
    public Campus updateCampus(Campus campus) {
        return campusRepository.updateCampus(campus);
    }

    @Override
    public void deleteCampus(Campus campus) {
        campusRepository.deleteCampus(campus);
    }

    @Override
    public List<Campus> searchCampus(String search) {
        return campusRepository.searchCampus(search);
    }
}
