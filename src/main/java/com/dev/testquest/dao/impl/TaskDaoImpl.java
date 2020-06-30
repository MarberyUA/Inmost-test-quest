package com.dev.testquest.dao.impl;

import com.dev.testquest.dao.TaskDao;
import com.dev.testquest.exception.DataProcessingException;
import com.dev.testquest.model.Status;
import com.dev.testquest.model.Task;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDaoImpl implements TaskDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Task create(Task task) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
            return task;
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
    public Task update(Task task) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
            return task;
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
    public void delete(Task task) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(task);
            transaction.commit();
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
    public List<Task> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Task> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Task.class);
            criteriaQuery.from(Task.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting all users!", e);
        }
    }

    @Override
    public List<Task> getTasksByStatus(Status status) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Task tk WHERE tk.status = :status";
            Query<Task> query = session.createQuery(hql);
            query.setParameter("status", status);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting user via email!", e);
        }
    }

    @Override
    public List<Task> getByUserId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Task tk WHERE tk.user.id = :id";
            Query<Task> query = session.createQuery(hql);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting task by user id", e);
        }
    }

    @Override
    public Task get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Task tk WHERE tk.id = :id";
            Query<Task> query = session.createQuery(hql);
            query.setParameter("id", id);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting task by id", e);
        }
    }

    @Override
    public List<Task> getTasksByOldUsers() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Task tk WHERE tk.user.dateCreation != :date";
            Query<Task> query = session.createQuery(hql);
            query.setParameter("date", LocalDate.now());
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting task by old users!", e);
        }
    }

    @Override
    public List<Task> getTasksByNewUsers() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Task tk WHERE tk.user.dateCreation = :date";
            Query<Task> query = session.createQuery(hql);
            query.setParameter("date", LocalDate.now());
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting task by old users!", e);
        }
    }
}
