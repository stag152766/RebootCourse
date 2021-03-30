package ru.course.assignment6.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.course.assignment6.entity.Parent;
import ru.course.assignment6.entity.School;

import java.util.List;

public class ParentDAO {

    public Parent findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Parent.class, id);
    }

    public void save(Parent parent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(parent);
        transaction1.commit();
        session.close();
    }


    public void update(Parent parent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.update(parent);
        transaction1.commit();
        session.close();
    }

    public void delete(Parent parent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.delete(parent);
        transaction1.commit();
        session.close();
    }

    // HQL queries are written in terms of entities, not tables,
    // therefore you can't query an arbitrary table table.
    public List<School> findAvailableSchoolForChild(Parent parent) {
        int parentId = parent.getId();
        String hql = "from School s where s.district in " +
                "(select p.district from Parent p join p.children c where c.school is null and p.id = :parentId)";
        List<School> schoolList = (List<School>) HibernateUtil
                .getSessionFactory()
                .openSession()
                .createQuery(hql)
                .setParameter("parentId", parentId)
                .list();
        return schoolList;
    }


}

