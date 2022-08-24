package pl.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hours {
    @Id
    private int id_h;
    private int godzina_od;
    private int godzina_do;

    public int getId_h() {
        return id_h;
    }

    public void setId_h(int id_h) {
        this.id_h = id_h;
    }

    public int getGodzina_od() {
        return godzina_od;
    }

    public void setGodzina_od(int godzina_od) {
        this.godzina_od = godzina_od;
    }

    public int getGodzina_do() {
        return godzina_do;
    }

    public void setGodzina_do(int godzina_do) {
        this.godzina_do = godzina_do;
    }
}
