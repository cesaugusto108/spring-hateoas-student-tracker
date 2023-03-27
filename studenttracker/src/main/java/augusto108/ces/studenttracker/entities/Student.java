package augusto108.ces.studenttracker.entities;

public class Student extends BaseEntity {
    private Name name;
    private String email;
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
