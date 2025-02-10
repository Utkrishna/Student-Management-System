package org.jsp.Project;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects", uniqueConstraints = @UniqueConstraint(columnNames = "SubjectName"))
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SubjectId")
    private Integer subjectId;

    @Column(name = "SubjectName", nullable = false, unique = true)
    private String subjectName;
    
    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    private List<Course> courses;

    public Subject() {}

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }
    
    // Getters and Setters
	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

   
    
}
