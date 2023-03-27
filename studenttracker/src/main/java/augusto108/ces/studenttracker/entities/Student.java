package augusto108.ces.studenttracker.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_student")
public class Student extends BaseEntity {
    @Embedded
    private Name name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "registration", unique = true, nullable = false)
    private String registration;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + name + " (" + registration + ")";
    }
}
