package augusto108.ces.studenttracker.services;

import augusto108.ces.studenttracker.entities.Student;
import augusto108.ces.studenttracker.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.getStudent(id);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.saveStudent(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.updateStudent(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.deleteStudent(student);
    }
}
