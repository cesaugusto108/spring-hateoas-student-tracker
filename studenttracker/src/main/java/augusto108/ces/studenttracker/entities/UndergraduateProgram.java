package augusto108.ces.studenttracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_undergraduate_program")
public class UndergraduateProgram extends BaseEntity {
    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @ManyToMany(mappedBy = "undergraduatePrograms", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
