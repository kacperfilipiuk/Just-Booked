package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Pokoje;
import com.example.mainApp.projekt_z_javy.entity.Rezerwacje;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;

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
