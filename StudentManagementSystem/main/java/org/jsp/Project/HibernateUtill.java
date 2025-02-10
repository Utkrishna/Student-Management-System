package org.jsp.Project;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtill {
	private static final SessionFactory sessionFactory;

    static {
        try {
        	Configuration configuration = new Configuration();
        	configuration.configure("hibernate.cfg.xml");

        	configuration.addAnnotatedClass(Student.class);
        	configuration.addAnnotatedClass(Address.class);
        	configuration.addAnnotatedClass(Course.class);
        	configuration.addAnnotatedClass(Subject.class);
        	configuration.addAnnotatedClass(ScMapping.class);

        	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        	builder.applySettings(configuration.getProperties());
        	sessionFactory = configuration.buildSessionFactory(builder.build());
        	
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
