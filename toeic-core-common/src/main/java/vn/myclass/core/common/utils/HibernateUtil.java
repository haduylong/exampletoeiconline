package vn.myclass.core.common.utils;
// khởi tạo session factory
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			// create session factory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable e) {
			System.out.println("Initial session factory fail");
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
