package org.ivalex.config;

import org.hibernate.Criteria;
import org.ivalex.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class UserDaoImpl {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("UsersDB");

    public static void add(User user) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public static List<User> listAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaQuery<User> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.select(criteriaQuery.from(User.class));

        List<User> listAll = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.getTransaction().commit();
        return listAll;
    }

    public static User showById(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        User ser = entityManager.find(User.class, id);
        entityManager.getTransaction().commit();
        return ser;
    }

    public static void update(Long id, User updatedUser) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        entityManager.merge(updatedUser);

        entityManager.getTransaction().commit();
        System.out.println("Это реально работает");
    }


    public static void delete(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        User user = showById(id);

        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));

        entityManager.getTransaction().commit();
    }

}
