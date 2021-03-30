package ru.course.assignment6.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.course.assignment6.entity.School;

public class SchoolDAO {

    public void save(School school){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(school);
        transaction1.commit();
        session.close();
    }


}
