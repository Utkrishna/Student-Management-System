package org.jsp.Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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
        int cId;

        switch (choice) {
            case 1:
                // Preload courses if none exist; this method is now non-interactive
                insertCoursesIfEmpty(session);
                
                Student st = new Student();
                System.out.println("Enter First Name: ");
                st.setFirstName(br.readLine());
                System.out.println("Enter Last Name: ");
                st.setLastName(br.readLine());
                System.out.println("Enter Email Address: ");
                st.setEmail(br.readLine());
                System.out.println("Enter Password: ");
                st.setPassword(br.readLine());
                System.out.println("Enter Date of Birth (MM/DD/YYYY): ");
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

                // Fetch available courses using a typed query
                Query<Course> courseQuery = session.createQuery("from Course", Course.class);
                List<Course> courseList = courseQuery.list();

                System.out.println("Following courses are available: ");
                for (Course course : courseList) {
                    System.out.println(course.getCourseId() + " " + course.getCourseName());
                }

                // Let the user choose a course (only one selection allowed)
                System.out.println("Enter Course Id: ");
                cId = Integer.parseInt(br.readLine());
                Course selectedCourse = session.get(Course.class, cId);
                if (selectedCourse != null) {
                    ScMapping mapping = new ScMapping();
                    mapping.setStudentMap(st);
                    mapping.setCourseMap(selectedCourse);
                    session.save(mapping);
                } else {
                    System.out.println("Invalid Course Id.");
                }
                System.out.println("Your student Id is: " + stuId);
                break;

            case 2:
                System.out.println("Enter Student Id: ");
                cId = Integer.parseInt(br.readLine());

                // Fetch student details with associated address
                Query<Student> studentQuery = session.createQuery(
                        "from Student A left join fetch A.addressList where A.studentId = :id", Student.class);
                studentQuery.setParameter("id", cId);
                List<Student> students = studentQuery.list();
                for (Student stud : students) {
                    if (stud.getStudentId().equals(cId)) {
                        System.out.println("Student Name: " + stud.getFirstName() + " " + stud.getLastName());
                        System.out.println("Student Email Address: " + stud.getEmail());
                        System.out.println("Date of Birth: " + stud.getDob());
                        System.out.println("Gender: " + stud.getGender());
                        if (stud.getAddressList() != null && !stud.getAddressList().isEmpty()) {
                            System.out.println("Address: " + stud.getAddressList().get(0).getAddress1());
                            System.out.println("City: " + stud.getAddressList().get(0).getCity());
                            System.out.println("State: " + stud.getAddressList().get(0).getState());
                            System.out.println("Country: " + stud.getAddressList().get(0).getCountry());
                            System.out.println("Zipcode: " + stud.getAddressList().get(0).getZip());
                        }
                    }
                }

                // Fetch course registrations for the student
                studentQuery = session.createQuery(
                        "from Student A left join fetch A.studentMapList where A.studentId = :id", Student.class);
                studentQuery.setParameter("id", cId);
                List<Student> studentMappings = studentQuery.list();
                for (Student stuM : studentMappings) {
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

    // Updated non-interactive method to preload courses and subjects
    static void insertCoursesIfEmpty(Session session) {
        Query<Long> courseCountQuery = session.createQuery("SELECT count(*) FROM Course", Long.class);
        Long courseCount = courseCountQuery.uniqueResult();
        if (courseCount == 0) {
            System.out.println("Preloading Courses and Subjects...");
            Subject java = new Subject("Java");
            Subject spring = new Subject("Spring Boot");
            Subject html = new Subject("HTML");
            Subject css = new Subject("CSS");
            Subject javaScript = new Subject("Java Script");
            Subject aptitude = new Subject("Aptitude");
            Subject nodejs = new Subject("NodeJS");
            Subject verbal = new Subject("Verbal Communication");
            Subject react = new Subject("ReactJS");
            Subject j2ee = new Subject("Advance Java");
            Subject hibernate = new Subject("Hibernate");
            Subject ds = new Subject("Data Structuer");
            Subject sql = new Subject("Sql");
            Subject python = new Subject("Python");
            Subject flask = new Subject("Flask");
            Subject Dejengo = new Subject("Dejango");
            Subject devopes = new Subject("Deveopes");
            Subject expressjs = new Subject("ExpressJS");
            Subject automation = new Subject("Automation Testing");
            Subject selenium = new Subject("Selenium");
            Subject api = new Subject("API Testing");
            Subject mobilet = new Subject("Mobile Testing");
            Subject numpy = new Subject("NumPY");
            Subject pands = new Subject("Pandas");
            Subject powerbi = new Subject("PowerBI");
            Subject excel = new Subject("Excel");
            Subject deeplerning = new Subject("Deep Lerning");
           

            session.save(java);
            session.save(spring);
            session.save(html);
            session.save(css);
            session.save(javaScript);
            session.save(aptitude);
            session.save(nodejs);
            session.save(verbal);
            session.save(react);
            session.save(j2ee);
            session.save(hibernate);
            session.save(ds);
            session.save(sql);
            session.save(python);
            session.save(flask);
            session.save(Dejengo);
            session.save(devopes);
            session.save(expressjs);
            session.save(automation);
            session.save(selenium);
            session.save(api);
            session.save(numpy);
            session.save(pands);
            session.save(powerbi);
            session.save(excel);
            session.save(deeplerning);
            // Using Arrays.asList for compatibility
            Course javaFullStack = new Course("Java Full Stack", "31000", "6 Months", "Benglore",
                    Arrays.asList(java, spring, hibernate,html,css,javaScript,aptitude,react,j2ee,ds,sql));
            Course pythonFullStack = new Course("Python Full Stack", "29000", "6 Months", "Benglore",
                    Arrays.asList(python,flask,Dejengo,sql,html,css,javaScript,aptitude,html,css,javaScript));
            Course mernStack = new Course("Mern Stack", "25000", "6 Months", "Benglore",
                    Arrays.asList(java,html,css,nodejs,sql,j2ee,hibernate,spring,aptitude,ds));
            Course devopes1 = new Course("Devopes", "25000", "6 Months", "Benglore",
                    Arrays.asList(java,j2ee,spring,hibernate,html,css,javaScript,react,sql,devopes,ds,aptitude));
            Course testing = new Course("Testing", "20000", "6 Months", "Benglore",
                    Arrays.asList(java,automation,selenium,sql,aptitude,api));
            Course dataAnalyst = new Course("Data Analyst", "35000", "5 Months", "Benglore",
                    Arrays.asList(python,numpy,pands,powerbi,excel,aptitude));
            Course dataScintist = new Course("Data Scintist", "45000", "6 Months", "Benglore",
                    Arrays.asList(python,numpy,pands,deeplerning,excel));

            session.save(javaFullStack);
            session.save(pythonFullStack);
            session.save(mernStack);
            session.save(devopes1);
            session.save(testing);
            session.save(dataAnalyst);
            session.save(dataScintist);

            session.getTransaction().commit();
            session.beginTransaction();
        }
    }
}
