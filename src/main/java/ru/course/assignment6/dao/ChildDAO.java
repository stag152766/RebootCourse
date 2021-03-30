package ru.course.assignment6.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.course.assignment6.entity.Child;

public class ChildDAO {

    public Child findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Child.class, id);
    }

    public void save(Child child) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(child);
        transaction1.commit();
        session.close();
    }


    public void update(Child child) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.update(child);
        transaction1.commit();
        session.close();
    }

    public void delete(Child child) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.delete(child);
        transaction1.commit();
        session.close();
    }
}
