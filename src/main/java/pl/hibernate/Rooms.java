package pl.hibernate;

import javax.persistence.*;
@Entity
public class Rooms {
    @Id
    private int id_p;
    private String nazwa;

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
