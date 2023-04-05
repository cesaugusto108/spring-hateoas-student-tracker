package augusto108.ces.studenttracker.repositories;

import augusto108.ces.studenttracker.entities.Campus;

import java.util.List;

public interface CampusRepository {
    Campus getCampus(Long id);

    List<Campus> getCampuses(int pageValue, int maxResults);

    Campus saveCampus(Campus campus);

    Campus updateCampus(Campus campus);

    void deleteCampus(Campus campus);

    List<Campus> searchCampus(String search);
}
