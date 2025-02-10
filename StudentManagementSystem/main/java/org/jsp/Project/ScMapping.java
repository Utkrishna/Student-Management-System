package org.jsp.Project;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Student_Course_Mapping")
public class ScMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RegistrationId")
    private Integer regId;

    @ManyToOne(fetch = FetchType.LAZY) // Optimize performance
    @JoinColumn(name = "StudentId", nullable = false)
    private Student studentMap;

    @ManyToOne
    @JoinColumn(name = "CourseId", nullable = false)
    private Course courseMap;

    // Getters and Setters
    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public Student getStudentMap() {
        return studentMap;
    }

    public void setStudentMap(Student studentMap) {
        this.studentMap = studentMap;
    }

    public Course getCourseMap() {
        return courseMap;
    }

    public void setCourseMap(Course courseMap) {
        this.courseMap = courseMap;
    }
}