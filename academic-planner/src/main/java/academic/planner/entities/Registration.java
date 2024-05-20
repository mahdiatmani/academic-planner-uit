package academic.planner.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="promotion_id", foreignKey=@ForeignKey(name="fk_promotion_student_promotion"), nullable=false)
    public Promotion promotion;

    @ManyToOne
    @JoinColumn(name="student_id", foreignKey=@ForeignKey(name="fk_promotion_student_student"), nullable=false)
    public Student student;

    @Column(name = "registration_date", nullable=false)
    private Date registrationDate;

    //--------------------------------------------------------------------------------
    // GETTER AND SETTERS
    //--------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
