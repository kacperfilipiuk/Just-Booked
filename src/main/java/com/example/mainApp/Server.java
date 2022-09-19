package com.example.mainApp;

import com.example.mainApp.projekt_z_javy.entity.Pokoje;
import com.example.mainApp.projekt_z_javy.entity.Wiadomosci;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    private static ServerSocket server;
    private static int port = 9876;


    //Naraize bez id uzytkownika
    public static void setMessage(String mess) {

        String wiadomoscOtrzymana = mess;
        String przedOstatnia = String.valueOf(wiadomoscOtrzymana.charAt(wiadomoscOtrzymana.length() - 2));
        int id_uzyt;

        //W przpadku indeksu jednoliczboweo
        if (!(przedOstatnia.equals("1") || przedOstatnia.equals("2") || przedOstatnia.equals("3") || przedOstatnia.equals("4") || przedOstatnia.equals("5") || przedOstatnia.equals("6") || przedOstatnia.equals("7") || przedOstatnia.equals("8") || przedOstatnia.equals("9"))) {
            String wez1 = wiadomoscOtrzymana.substring(wiadomoscOtrzymana.length() - 1);
            wiadomoscOtrzymana = wiadomoscOtrzymana.substring(0,wiadomoscOtrzymana.length()-1);
            System.out.println(wiadomoscOtrzymana);
            System.out.println(wez1);
            id_uzyt = Integer.parseInt(wez1);
        } else {
            //W przypadku indeksu dwuliczbowego
            String wez2 = wiadomoscOtrzymana.substring(wiadomoscOtrzymana.length() - 2);
            wiadomoscOtrzymana = wiadomoscOtrzymana.substring(0,wiadomoscOtrzymana.length()-2);
            System.out.println(wiadomoscOtrzymana);
            System.out.println(wez2);
            id_uzyt = Integer.parseInt(wez2);
        }


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Wiadomosci wiadomosci = new Wiadomosci();
            //Id powinno ustaiwać się samo
            //wiadomosci.setIdW(1);
            wiadomosci.setWiadomosc(wiadomoscOtrzymana);
            wiadomosci.setIdU(id_uzyt);
            entityManager.persist(wiadomosci);
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

    /**
     * id uzytkownika dodac do wiadomosci na koniec i potem usunac ti przed dodanie mdo bazy
     */


    public static void main(String args[]) throws IOException, ClassNotFoundException {
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        while (true) {
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            setMessage(message);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //write object to Socket
            oos.writeObject("Hi Client, twoja wiadomosc zostala wyslana");
            //close resources
            ois.close();
            oos.close();
            socket.close();
            //terminate the server if client sends exit request
            if (message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }

}

