package augusto108.ces.studenttracker.repositories;

import augusto108.ces.studenttracker.entities.Student;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final SessionFactory sessionFactory;

    public StudentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Student getStudent(Long id) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Student s where id = :id", Student.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Student> getStudents(int pageValue, int maxResults) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Student order by registration", Student.class)
                .setFirstResult(pageValue * maxResults)
                .setMaxResults(maxResults)
                .getResultList();
    }

    @Override
    public Student saveStudent(Student student) {
        sessionFactory
                .getCurrentSession()
                .save(student);

        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        sessionFactory
                .getCurrentSession()
                .update(student);

        return student;
    }

    @Override
    public void deleteStudent(Student student) {
        sessionFactory
                .getCurrentSession()
                .delete(student);
    }

    @Override
    public List<Student> searchStudents(String search) {
        return sessionFactory
                .getCurrentSession()
                .createQuery(
                        "from Student where lower(first_name) like :search " +
                                "or lower(last_name) like :search " +
                                "or lower(email) like :search " +
                                "or registration like :search",
                        Student.class
                )
                .setParameter("search", "%" + search.toLowerCase().trim() + "%")
                .getResultList();
    }
}
