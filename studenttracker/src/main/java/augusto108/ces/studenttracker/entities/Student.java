package augusto108.ces.studenttracker.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_student")
public class Student extends BaseEntity {
    @Embedded
    private Name name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "registration", unique = true, nullable = false)
    private String registration;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_student_address",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses = new HashSet<>();

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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + name + " (" + registration + ")";
    }
}
