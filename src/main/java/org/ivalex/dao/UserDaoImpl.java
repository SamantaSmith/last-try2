package org.ivalex.dao;

import org.ivalex.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("UsersDB");

    public static void add(User user) {


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
    }

    public static List<User> listAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> listAll = new ArrayList<>();

        try {
            entityManager.getTransaction().begin();
            CriteriaQuery<User> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(User.class);
            criteriaQuery.select(criteriaQuery.from(User.class));

            listAll = entityManager.createQuery(criteriaQuery).getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
        return listAll;
    }

    public static User showById(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User ser = new User();
        try {
            entityManager.getTransaction().begin();

            ser = entityManager.find(User.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }

        return ser;
    }


    public static void update(Long id, User updatedUser) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();


            entityManager.merge(updatedUser);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }

    }


    public static void delete(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            User user = showById(id);
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
    }

}
