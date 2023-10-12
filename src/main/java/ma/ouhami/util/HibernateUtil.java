package ma.ouhami.util;

import ma.ouhami.entities.Client;
import ma.ouhami.entities.Employee;
import ma.ouhami.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author ay0ub
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    // using the hibernate native apis
    static {
        if (sessionFactory == null) {
            final StandardServiceRegistry registry
                    = new StandardServiceRegistryBuilder()
                            .build();
            try {
                sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Client.class)
                        .addAnnotatedClass(Employee.class)
                        .buildMetadata()
                        .buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
