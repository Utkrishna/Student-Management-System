# Student-Management-System


📌 Project Description

The Student Management System is a Java-based application that uses Hibernate and MySQL to manage student records. It allows users to add, view, and enroll students in courses, while maintaining relationships between students, addresses, and courses.

🛠️ Technologies Used
Java (JDK 8+)
Hibernate ORM (v5.6+)
MySQL (Database)
Maven (Dependency Management)
JDBC Driver (MySQL Connector)

📂 Project Structure

student-management-system/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── org.jsp.Project/
│   │   │   │   ├── HibernateProj.java (Main Class)
│   │   │   │   ├── HibernateUtil.java (Session Factory)
│   │   │   │   |___TestTheapp.java
│   │   │   │   │── Student.java
│   │   │   │   │── Address.java
│   │   │   │   │── Course.java
│   │   │   │   │── ScMapping.java
│   ├── resources/
│   │   ├── hibernate.cfg.xml (Hibernate Configuration)
│── pom.xml (Maven Dependencies)
│── README.md (Project Documentation)

💡 Features

✅ Add new students with personal details and address.✅ Enroll students into courses.✅ View student details including address and enrolled courses.✅ Uses Hibernate ORM for database operations.✅ MySQL integration with automatic table creation.
