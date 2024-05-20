package academic.planner.entities;

import jakarta.persistence.*;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="promotion_id", foreignKey=@ForeignKey(name="fk_Schedule_promotion"), nullable=false)
    public Promotion promotion;

    @ManyToOne
    @JoinColumn(name="class_room_id", foreignKey=@ForeignKey(name="fk_Schedule_class_room"), nullable=false)
    public ClassRoom classRoom;

    @Column(name = "day_of_week")
    private String dayOfWeek; // "Monday", "Tuesday", etc.

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

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

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
