package augusto108.ces.studenttracker.repositories;

import augusto108.ces.studenttracker.entities.UndergraduateProgram;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UndergraduateProgramRepositoryImpl implements UndergraduateProgramRepository {
    private final SessionFactory sessionFactory;

    public UndergraduateProgramRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UndergraduateProgram getUndergraduateProgram(Long id) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from UndergraduateProgram u where id = :id", UndergraduateProgram.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<UndergraduateProgram> getUndergraduatePrograms(int pageValue, int maxResults) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from UndergraduateProgram order by description", UndergraduateProgram.class)
                .setFirstResult(pageValue * maxResults)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public UndergraduateProgram saveUndergraduateProgram(UndergraduateProgram undergraduateProgram) {
        sessionFactory
                .getCurrentSession()
                .save(undergraduateProgram);

        return undergraduateProgram;
    }

    @Override
    public UndergraduateProgram updateUndergraduateProgram(UndergraduateProgram undergraduateProgram) {
        sessionFactory
                .getCurrentSession()
                .update(undergraduateProgram);

        return undergraduateProgram;
    }

    @Override
    public void deleteUndergraduateProgram(UndergraduateProgram undergraduateProgram) {
        sessionFactory
                .getCurrentSession()
                .delete(undergraduateProgram);
    }

    @Override
    public List<UndergraduateProgram> searchUndergraduatePrograms(String search) {
        return sessionFactory
                .getCurrentSession()
                .createQuery(
                        "from UndergraduateProgram u where lower(description) like :search",
                        UndergraduateProgram.class
                )
                .setParameter("search", "%" + search.toLowerCase().trim() + "%")
                .getResultList();
    }
}
