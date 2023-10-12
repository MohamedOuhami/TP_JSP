package ma.ouhami.services;

import java.util.List;
import ma.ouhami.dao.IDao;
import ma.ouhami.entities.User;
import ma.ouhami.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserService implements IDao<User> {

    public UserService() {
    }

    @Override
    public boolean create(User o) {
        Session session = null; // Declare session outside the try block
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(User o) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public User findById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(User.class, id);
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<User> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from User", User.class).list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(User o) {
        Session session = null; // Declare session outside the try block
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public User findByEmail(String email) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<User> query = session.createQuery("FROM User u WHERE u.email = :email");
            query.setParameter("email", email);
            return query.uniqueResult();
            
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
