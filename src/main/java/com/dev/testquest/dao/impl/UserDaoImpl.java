package com.dev.testquest.dao.impl;

import com.dev.testquest.dao.UserDao;
import com.dev.testquest.exception.DataProcessingException;
import com.dev.testquest.model.User;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User create(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while creating user!", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User update(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while creating user!", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(User.class);
            criteriaQuery.from(User.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting all users!", e);
        }
    }

    @Override
    public void delete(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while creating user!", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User us JOIN fetch us.roles WHERE us.email = :email";
            Query<User> query = session.createQuery(hql);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting user via email!", e);
        }
    }

    @Override
    public List<User> paginatedUserList(Integer page) {
        try (Session session = sessionFactory.openSession()) {
            List<User> users = session.createQuery("from User us", User.class)
                    .setMaxResults(10)
                    .setFirstResult(page * 10)
                    .getResultList();
            return users;
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting users from db!", e);
        }
    }
}
