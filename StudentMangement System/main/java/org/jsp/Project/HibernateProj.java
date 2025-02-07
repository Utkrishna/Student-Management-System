package org.jsp.Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jsp.Project.Address;
import org.jsp.Project.Course;
import org.jsp.Project.HibernateUtill;
import org.jsp.Project.ScMapping;
import org.jsp.Project.Student;

public class HibernateProj {
    public static void main(String[] args) throws IOException {
        SessionFactory factory = HibernateUtill.getSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        int stuId;
        System.out.println("1. Enter Data");
        System.out.println("2. View Data");
        System.out.println("Enter a choice from the above: ");
        
        choice = Integer.parseInt(br.readLine());
        String ch = "Y";
        int cId;

        switch (choice) {
            case 1:
            	insertCoursesIfEmpty(session, br);
                Student st = new Student();
                System.out.println("Enter First Name: ");
                st.setFirstName(br.readLine());
                System.out.println("Enter Last Name: ");
                st.setLastName(br.readLine());
                System.out.println("Enter  Email address: ");
                st.setEmail(br.readLine());
                System.out.println("Enter Password: ");
                st.setPassword(br.readLine());
                System.out.println("Enter Date of Birth(MM/DD/YYYY): ");
                st.setDob(br.readLine());
                System.out.println("Enter Gender: ");
                st.setGender(br.readLine());

                session.save(st);
                stuId = st.getStudentId();

                // Address entry
                Address add = new Address();
                System.out.println("Enter Address: ");
                add.setAddress1(br.readLine());
                System.out.println("Enter City: ");
                add.setCity(br.readLine());
                System.out.println("Enter State: ");
                add.setState(br.readLine());
                System.out.println("Enter Country: ");
                add.setCountry(br.readLine());
                System.out.println("Enter Zipcode: ");
                add.setZip(br.readLine());
                add.setStudent(st);

                session.save(add);

                // Fetch courses
                Criteria criteria = session.createCriteria(Course.class);
                List<Course> courseList = criteria.list();

                System.out.println("Following courses are available: ");
                for (Course course : courseList) {
                    System.out.println(course.getCourseId() + " " + course.getCourseName());
                }

                // Map courses to student
                while (ch.equalsIgnoreCase("Y")) {
                    System.out.println("Enter Course Id: ");
                    cId = Integer.parseInt(br.readLine());
                    Course cc = (Course) session.get(Course.class, cId);
                    if (cc != null) {
                        ScMapping mapp = new ScMapping();
                        mapp.setStudentMap(st);
                        mapp.setCourseMap(cc);
                        session.save(mapp);
                    } else {
                        System.out.println("Invalid Course Id. Try again.");
                    }
                    System.out.println("Do you want to enter more courses (Y/N):");
                    ch = br.readLine();
                }
                System.out.println("Your student Id is: " + stuId);
                break;

            case 2:
                System.out.println("Enter Student Id: ");
                cId = Integer.parseInt(br.readLine());

                // Fetch student details
                Query query = session.createQuery("from Student A left join fetch A.addressList where A.studentId = :id");
                query.setParameter("id", cId);
                List<Student> stu = query.list();
                for (Student stud : stu) {
                    if (stud.getStudentId().equals(cId)) {
                        System.out.println("Student Name: " + stud.getFirstName() + " " + stud.getLastName());
                        System.out.println("Student Eamil Address: " + stud.getEmail());
                        System.out.println("Date of Birth: " + stud.getDob());
                        System.out.println("Gender: " + stud.getGender());
                        System.out.println("Address: " + stud.getAddressList().get(0).getAddress1());
                        System.out.println("City: " + stud.getAddressList().get(0).getCity());
                        System.out.println("State: " + stud.getAddressList().get(0).getState());
                        System.out.println("Country: " + stud.getAddressList().get(0).getCountry());
                        System.out.println("Zipcode: " + stud.getAddressList().get(0).getZip());
                    }
                }

                // Fetch course registrations
                query = session.createQuery("from Student A left join fetch A.studentMapList where A.studentId = :id");
                query.setParameter("id", cId);
                List<Student> stuMap = query.list();
                for (Student stuM : stuMap) {
                    if (stuM.getStudentId().equals(cId)) {
                        for (ScMapping mapping : stuM.getStudentMapList()) {
                            System.out.println("Registration Id: " + mapping.getRegId());
                            System.out.println("Course Id: " + mapping.getCourseMap().getCourseId());
                            System.out.println("Course Name: " + mapping.getCourseMap().getCourseName());
                            System.out.println("Course Duration: " + mapping.getCourseMap().getDuration());
                            System.out.println("Course Location: " + mapping.getCourseMap().getLocation());
                            System.out.println("Course Fees: " + mapping.getCourseMap().getFees());
                            System.out.println();
                        }
                    }
                }
                break;
        }

        session.getTransaction().commit();
        session.close();

        System.out.println("Exit");
    }

    // Method to insert courses if none exist
    private static void insertCoursesIfEmpty(Session session, BufferedReader br) throws IOException {
        Query courseCountQuery = session.createQuery("SELECT count(*) FROM Course");
        Long courseCount = (Long) courseCountQuery.uniqueResult();
        if (courseCount == 0) {
            System.out.println("No courses available. Please add some courses.");
            String addMore = "Y";
            while (addMore.equalsIgnoreCase("Y")) {
                Course newCourse = new Course();
                System.out.println("Enter Course Name: ");
                newCourse.setCourseName(br.readLine());
                System.out.println("Enter Course Durations: ");
                newCourse.setDuration(br.readLine());
                System.out.println("Enter Course Location: ");
                newCourse.setLocation(br.readLine());
                System.out.println("Enter Fees: ");
                newCourse.setFees(br.readLine());

                session.save(newCourse);
                System.out.println("Course added with ID: " + newCourse.getCourseId());

                System.out.println("Do you want to add another course? (Y/N): ");
                addMore = br.readLine();
            }
            session.getTransaction().commit();
            session.beginTransaction(); // Restart transaction after committing courses
        }
    }
}
