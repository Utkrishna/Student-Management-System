package org.jsp.Project;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses", uniqueConstraints = @UniqueConstraint(columnNames = "CourseName"))
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseId")
    private Integer courseId;

    @Column(name = "CourseName", nullable = false, unique = true)
    private String courseName;

    @Column(name = "Fees", nullable = false)
    private String fees;

    @Column(name = "Duration", nullable = false)
    private String duration;

    @Column(name = "Location", nullable = false)
    private String location;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "course_subject",
        joinColumns = @JoinColumn(name = "CourseId"),
        inverseJoinColumns = @JoinColumn(name = "SubjectId")
    )
    private List<Subject> subjects;

    public Course() {}

    public Course(String courseName, String fees, String duration, String location, List<Subject> subjects) {
        this.courseName = courseName;
        this.fees = fees;
        this.duration = duration;
        this.location = location;
        this.subjects = subjects;
    }
    // Getters and Setters
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

 
}
