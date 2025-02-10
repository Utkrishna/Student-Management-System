package org.jsp.Project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Arrays;

public class TestStudentFile {
    public static void main(String[] args) {
        // Build session factory and open session
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        // Insert courses if empty (this method preloads courses with predefined subjects)
        HibernateProj hs=new HibernateProj();
       hs.insertCoursesIfEmpty(session);
        
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    
}
