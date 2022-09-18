package com.example.mainApp;

import java.sql.Date;

public class Rezerwacja {
    String id_rezerwacji;
    String id_pokoju;
    String id_uzyt;
    String id_godz;
    Date data;

    public Rezerwacja(String id_rezerwacji, String id_pokoju, String id_uzyt, String id_godz, Date data) {
        this.id_rezerwacji = id_rezerwacji;
        this.id_pokoju = id_pokoju;
        this.id_uzyt = id_uzyt;
        this.id_godz = id_godz;
        this.data = data;
    }

    public String getId_rezerwacji() {
        return id_rezerwacji;
    }

    public void setId_rezerwacji(String id_rezerwacji) {
        this.id_rezerwacji = id_rezerwacji;
    }

    public String getId_pokoju() {
        return id_pokoju;
    }

    public void setId_pokoju(String id_pokoju) {
        this.id_pokoju = id_pokoju;
    }

    public String getId_uzyt() {
        return id_uzyt;
    }

    public void setId_uzyt(String id_uzyt) {
        this.id_uzyt = id_uzyt;
    }

    public String getId_godz() {
        return id_godz;
    }

    public void setId_godz(String id_godz) {
        this.id_godz = id_godz;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Rezerwacja{" +
                "id_rezerwacji=" + id_rezerwacji +
                ", id_pokoju=" + id_pokoju +
                ", id_uzyt=" + id_uzyt +
                ", id_godz=" + id_godz +
                ", data=" + data +
                '}';
    }
}
