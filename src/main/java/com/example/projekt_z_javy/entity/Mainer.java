package com.example.projekt_z_javy.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.math.BigInteger;

public class Mainer {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit") ;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            Uzytkownicy uzy = new Uzytkownicy();
            Godziny go = new Godziny();
            go.setGodzinaOd(BigInteger.valueOf(22));
            go.setGodzinaDo(BigInteger.valueOf(00));
            System.out.println(go);
            entityManager.merge(go);

            entityTransaction.commit();
        } finally {
            if(entityTransaction.isActive())
                entityTransaction.rollback();
        }
        entityManager.close();
        entityManagerFactory.close();
    }



    /*
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        com.example.projekt_z_javy.entity.Uzytkownicy uzy = new Uzytkownicy(27,"Adam", "adamek", "adamek@o2.pl");
        session.save(uzy);
        System.out.println(uzy);

        session.getTransaction().commit();
        session.close();
        }
     */

}
