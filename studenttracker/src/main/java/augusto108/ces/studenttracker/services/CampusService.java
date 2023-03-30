package augusto108.ces.studenttracker.services;

import augusto108.ces.studenttracker.entities.Campus;

import java.util.List;

public interface CampusService {
    Campus getCampus(Long id);

    List<Campus> getCampuses();

    Campus saveCampus(Campus campus);

    Campus updateCampus(Campus campus);

    void deleteCampus(Campus campus);

    List<Campus> searchCampus(String search);
}
