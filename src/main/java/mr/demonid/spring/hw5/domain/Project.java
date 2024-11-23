package mr.demonid.spring.hw5.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_project")
    private String name;

    @Column(name = "description", length = 2000)
    private String desc;

    @Column(name = "created_date")
    private LocalDate createdDate;

    public Project(String name, String desc, LocalDate createdDate) {
        this.name = name;
        this.desc = desc;
        this.createdDate = createdDate;
    }

}
