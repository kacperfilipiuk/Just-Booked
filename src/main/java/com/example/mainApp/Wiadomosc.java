package com.example.mainApp;

public class Wiadomosc {
    String id_wiadomosci;
    String id_uzytkownika;
    String pelna_wiadomosc;

    public Wiadomosc(String id_wiadomosci, String id_uzytkownika, String pelna_wiadomosc) {
        this.id_wiadomosci = id_wiadomosci;
        this.id_uzytkownika = id_uzytkownika;
        this.pelna_wiadomosc = pelna_wiadomosc;
    }

    public String getId_wiadomosci() {
        return id_wiadomosci;
    }

    public String getPelna_wiadomosc() {
        return pelna_wiadomosc;
    }

    @Override
    public String toString() {
        return "Wiadomosc{" +
                "id_wiadomosci='" + id_wiadomosci + '\'' +
                ", id_uzytkownika='" + id_uzytkownika + '\'' +
                ", pelna_wiadomosc='" + pelna_wiadomosc + '\'' +
                '}';
    }
}
