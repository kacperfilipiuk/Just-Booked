package com.example.mainApp;

import java.sql.Date;

public class Rezerwacja {
    int id_rezerwacji;
    int id_pokoju;
    int id_uzyt;
    int id_godz;
    Date data;

    public Rezerwacja(int id_rezerwacji, int id_pokoju, int id_uzyt, int id_godz, Date data) {
        this.id_rezerwacji = id_rezerwacji;
        this.id_pokoju = id_pokoju;
        this.id_uzyt = id_uzyt;
        this.id_godz = id_godz;
        this.data = data;
    }

    public int getId_rezerwacji() {
        return id_rezerwacji;
    }

    public void setId_rezerwacji(int id_rezerwacji) {
        this.id_rezerwacji = id_rezerwacji;
    }

    public int getId_pokoju() {
        return id_pokoju;
    }

    public void setId_pokoju(int id_pokoju) {
        this.id_pokoju = id_pokoju;
    }

    public int getId_uzyt() {
        return id_uzyt;
    }

    public void setId_uzyt(int id_uzyt) {
        this.id_uzyt = id_uzyt;
    }

    public int getId_godz() {
        return id_godz;
    }

    public void setId_godz(int id_godz) {
        this.id_godz = id_godz;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
