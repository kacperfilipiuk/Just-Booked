package pl.hibernate;

import javafx.scene.chart.PieChart;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Reservations {
    @Id
    private int id_rez;
    private int id_p;
    private int id_u;
    private int id_h;
    private Date data;

    public int getId_rez() {
        return id_rez;
    }

    public void setId_rez(int id_rez) {
        this.id_rez = id_rez;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public int getId_h() {
        return id_h;
    }

    public void setId_h(int id_h) {
        this.id_h = id_h;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
