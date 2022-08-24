package pl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Menager {
    public static void main(String[] args) {

        //Tworzymy obiekt configuration
        Configuration configuration = new Configuration();
        //Wczytanie pliku konfiguracyjnego
        configuration.configure("hibernate.cfg.xml");
        //Wczytanie adnotacji
        configuration.addAnnotatedClass(Users.class);
        //Stworzenie obiketu SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //Pobieranie sesji
        Session session = sessionFactory.getCurrentSession();
        //stworzenie obiektu klasy user
        Users users = new Users();
        //users.setId_u(38);
        users.setLogin("Mikolaj");
        users.setEmail("mikolaj@o2.pl");
        users.setHaslo("12345");
        //rozpoczniemy tranzakcje
        session.beginTransaction();
        //Dodamy i zpaiszemy uzytkownika
        //session.save(users);
        session.persist(users);
        //Zakonczymy tranzakcje
        session.getTransaction().commit();
        //zamkniecie obiektu session factory
        sessionFactory.close();

    }
}
