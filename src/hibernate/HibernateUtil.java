package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static SessionFactory sessionFactory = new AnnotationConfiguration().configure()
            .buildSessionFactory();
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}