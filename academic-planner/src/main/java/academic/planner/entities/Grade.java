package academic.planner.entities;

import jakarta.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="promotion_id", foreignKey=@ForeignKey(name="fk_grade_promotion"), nullable=false)
    public Promotion promotion;

    @ManyToOne
    @JoinColumn(name="student_id", foreignKey=@ForeignKey(name="fk_grade_student"), nullable=false)
    private Student student;

    @ManyToOne
    @JoinColumn(name="course_id", foreignKey=@ForeignKey(name="fk_grade_course"), nullable=false)
    private Course course;

    @Column(name = "grade", nullable=false)
    private Double grade;

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
