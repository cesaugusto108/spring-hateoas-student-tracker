package augusto108.ces.studenttracker.repositories;

import augusto108.ces.studenttracker.entities.Campus;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CampusRepositoryImpl implements CampusRepository {
    private final SessionFactory sessionFactory;

    public CampusRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Campus getCampus(Long id) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Campus c where id = :id", Campus.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Campus> getCampuses(int pageValue, int maxResults) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Campus order by description", Campus.class)
                .setFirstResult(pageValue * maxResults)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public Campus saveCampus(Campus campus) {
        sessionFactory
                .getCurrentSession()
                .save(campus);

        return campus;
    }

    @Override
    public Campus updateCampus(Campus campus) {
        sessionFactory
                .getCurrentSession()
                .update(campus);

        return campus;
    }

    @Override
    public void deleteCampus(Campus campus) {
        sessionFactory
                .getCurrentSession()
                .delete(campus);
    }

    @Override
    public List<Campus> searchCampus(String search) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Campus c where lower(description) like :search", Campus.class)
                .setParameter("search", "%" + search.toLowerCase().trim() + "%")
                .getResultList();
    }
}
