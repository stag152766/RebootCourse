package ru.course.assignment6.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.course.assignment6.entity.District;

public class DistrictDAO {

    public void save(District district) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(district);
        transaction1.commit();
        session.close();
    }
}
