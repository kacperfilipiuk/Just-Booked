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
