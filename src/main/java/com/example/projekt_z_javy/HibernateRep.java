package com.example.projekt_z_javy;

import org.hibernate.Session;

import static com.example.projekt_z_javy.HibernateUtil.getSessionFactory;

public class HibernateRep {
    public static void writeToDatabase(String userName, String userPassword,  String userEmail){
        final Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction(); //Tranzakcja - musi sie wykonać cały poroces albo nic
        sesion.persist(userName);
        sesion.persist(userPassword);
        sesion.getTransaction().commit();

    }
}
