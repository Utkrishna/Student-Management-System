package org.jsp.Project;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "courses") // Changed table name to avoid conflicts
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseId")
    private Integer courseId;

    @Column(name = "CourseName", nullable = false)
    private String courseName;

    @Column(name = "fees", nullable = false)
    private String fees;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "Location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "courseMap", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScMapping> courseMapList;

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId; // Hibernate will assign it automatically
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<ScMapping> getCourseMapList() {
        return courseMapList;
    }

    public void setCourseMapList(List<ScMapping> courseMapList) {
        this.courseMapList = courseMapList;
    }
}
