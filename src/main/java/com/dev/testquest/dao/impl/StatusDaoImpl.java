package com.dev.testquest.dao.impl;

import com.dev.testquest.dao.StatusDao;
import com.dev.testquest.exception.DataProcessingException;
import com.dev.testquest.model.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatusDaoImpl implements StatusDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Status create(Status status) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(status);
            transaction.commit();
            return status;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while creating status!", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Status getByStatusName(String statusName) {
        Status.StatusName enumStatusName;
        try {
            enumStatusName = Status.StatusName.valueOf(statusName);
        } catch (IllegalArgumentException e) {
            throw new DataProcessingException("There is not such role", e);
        }
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "FROM Status st WHERE st.statusName = :status";
            Query<Status> query = session.createQuery(hqlQuery, Status.class);
            query.setParameter("status", enumStatusName);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting role from db", e);
        }
    }
}
