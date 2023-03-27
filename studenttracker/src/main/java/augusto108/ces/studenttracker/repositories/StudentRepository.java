package augusto108.ces.studenttracker.repositories;

import augusto108.ces.studenttracker.entities.Student;

import java.util.List;

public interface StudentRepository {
    Student getStudent(Long id);

    List<Student> getStudents();

    Student saveStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(Student student);
}
