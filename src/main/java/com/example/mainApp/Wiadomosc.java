package com.example.mainApp;

public class Wiadomosc {
    String id_wiadomosci;
    String id_uzytwkonika;
    String pelna_wiadomosc;

    public Wiadomosc(String id_wiadomosci, String id_uzytwkonika, String pelna_wiadomosc) {
        this.id_wiadomosci = id_wiadomosci;
        this.id_uzytwkonika = id_uzytwkonika;
        this.pelna_wiadomosc = pelna_wiadomosc;
    }

    public String getId_wiadomosci() {
        return id_wiadomosci;
    }

    public void setId_wiadomosci(String id_wiadomosci) {
        this.id_wiadomosci = id_wiadomosci;
    }

    public String getId_uzytwkonika() {
        return id_uzytwkonika;
    }

    public void setId_uzytwkonika(String id_uzytwkonika) {
        this.id_uzytwkonika = id_uzytwkonika;
    }

    public String getPelna_wiadomosc() {
        return pelna_wiadomosc;
    }

    public void setPelna_wiadomosc(String pelna_wiadomosc) {
        this.pelna_wiadomosc = pelna_wiadomosc;
    }

    @Override
    public String toString() {
        return "Wiadomosc{" +
                "id_wiadomosci='" + id_wiadomosci + '\'' +
                ", id_uzytwkonika='" + id_uzytwkonika + '\'' +
                ", pelna_wiadomosc='" + pelna_wiadomosc + '\'' +
                '}';
    }
}
