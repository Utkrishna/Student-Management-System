package org.jsp.Project;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students") // Changed table name to lowercase for consistency
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentId")
    private Integer studentId;

    @Column(name = "First_name", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;
    
    @Column(name = "email", nullable = false ,unique=true)
    private String email;
    
    @Column(name = "Password", nullable = false)
    private String Password;
    
    @Column(name = "DOB", nullable = false)
    private String dob;

    @Column(name = "Gender", nullable = false)
    private String gender;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addressList;

    @OneToMany(mappedBy = "studentMap", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScMapping> studentMapList;

    // Getters and Setters
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<ScMapping> getStudentMapList() {
        return studentMapList;
    }

    public void setStudentMapList(List<ScMapping> studentMapList) {
        this.studentMapList = studentMapList;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
