package academic.planner.entities;

import jakarta.persistence.*;

@Entity
public class AcademicProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code" ,nullable=false, unique = true)
    private String code;

    @Column(name = "name" ,nullable=false)
    private String name;

    @Lob
    @Column(name = "description", nullable=false)
    private String description;

    @ManyToOne
    @JoinColumn(name="department_id", foreignKey=@ForeignKey(name="fk_academic_program_department"), nullable=false)
    public Department department;

    @ManyToOne
    @JoinColumn(name="degree_id", foreignKey=@ForeignKey(name="fk_academic_program_degree"), nullable=false)
    public Degree degree;

    //--------------------------------------------------------------------------------
    // GETTER AND SETTERS
    //--------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }
}
