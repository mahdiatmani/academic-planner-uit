package academic.planner.entities;

import jakarta.persistence.*;

@Entity
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable=false)
    private Long id;

    @Column(name = "code", nullable=false, unique = true)
    private String code;

    @Column(name = "name", nullable=false)
    private String name;

    @ManyToOne
    @JoinColumn(name="establishment_id", foreignKey=@ForeignKey(name="fk_class_room_establishment"), nullable=false)
    public Establishment establishment;

    @ManyToOne
    @JoinColumn(name="department_id", foreignKey=@ForeignKey(name="fk_class_room_department"), nullable=true)
    public Department department;

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

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
