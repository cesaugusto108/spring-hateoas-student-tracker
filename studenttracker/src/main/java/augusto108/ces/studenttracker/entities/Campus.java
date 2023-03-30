package augusto108.ces.studenttracker.entities;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "tb_campus")
public class Campus extends BaseEntity {
    @Column(name = "description", unique = true, nullable = false)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
