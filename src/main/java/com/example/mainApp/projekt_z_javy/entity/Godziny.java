package com.example.mainApp.projekt_z_javy.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Objects;

 /**@Class Godziny - zrzutowana encja (tabela) na klase */

@Entity
public class Godziny {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_h")
    private int idH;
    @Basic
    @Column(name = "godzina_od")
    private Integer godzinaOd;
    @Basic
    @Column(name = "godzina_do")
    private Integer godzinaDo;
    @OneToMany(mappedBy = "godzinyByIdH")
    private Collection<Rezerwacje> rezerwacjesByIdH;

    public Godziny() {
    }

    public int getIdH() {
        return idH;
    }

    public void setIdH(int idH) {
        this.idH = idH;
    }

    public Integer getGodzinaOd() {
        return godzinaOd;
    }

    public void setGodzinaOd(Integer godzinaOd) {
        this.godzinaOd = godzinaOd;
    }

    public Integer getGodzinaDo() {
        return godzinaDo;
    }

    public void setGodzinaDo(Integer godzinaDo) {
        this.godzinaDo = godzinaDo;
    }

    @Override
    public String toString() {
        return "Godziny{" +
                "idH=" + idH +
                ", godzinaOd=" + godzinaOd +
                ", godzinaDo=" + godzinaDo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Godziny godziny = (Godziny) o;
        return idH == godziny.idH && Objects.equals(godzinaOd, godziny.godzinaOd) && Objects.equals(godzinaDo, godziny.godzinaDo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idH, godzinaOd, godzinaDo);
    }

    public Collection<Rezerwacje> getRezerwacjesByIdH() {
        return rezerwacjesByIdH;
    }

    public void setRezerwacjesByIdH(Collection<Rezerwacje> rezerwacjesByIdH) {
        this.rezerwacjesByIdH = rezerwacjesByIdH;
    }
}
