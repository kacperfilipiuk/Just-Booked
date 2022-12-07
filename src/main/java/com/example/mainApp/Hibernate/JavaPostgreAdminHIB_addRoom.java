package com.example.mainApp.Hibernate;

import com.example.mainApp.Entity.Pokoje;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JavaPostgreAdminHIB_addRoom {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");

    public static void writeRoomToDatabase(String rN) {

        String roomName = rN;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Pokoje pokoje = new Pokoje();
            //Id powinno ustaiwać się samo
            pokoje.setNazwa(roomName);
            entityManager.persist(pokoje);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
