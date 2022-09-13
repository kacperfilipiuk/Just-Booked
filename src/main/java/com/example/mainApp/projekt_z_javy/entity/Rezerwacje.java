package com.example.mainApp.projekt_z_javy.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Rezerwacje {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rez")
    private int idRez;
    @Basic
    @Column(name = "id_p")
    private int idP;
    @Basic
    @Column(name = "id_u")
    private int idU;
    @Basic
    @Column(name = "id_h")
    private int idH;
    @Basic
    @Column(name = "data")
    private Date data;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_p", referencedColumnName = "id_p", nullable = false, insertable = false, updatable = false)
    private Pokoje pokojeByIdP;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_u", referencedColumnName = "id_u", nullable = false, insertable = false, updatable = false)
    private Uzytkownicy uzytkownicyByIdU;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_h", referencedColumnName = "id_h", insertable = false, updatable = false)
    private Godziny godzinyByIdH;

    public int getIdRez() {
        return idRez;
    }

    public void setIdRez(int idRez) {
        this.idRez = idRez;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdH() {
        return idH;
    }

    public void setIdH(int idH) {
        this.idH = idH;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Rezerwacje() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rezerwacje that = (Rezerwacje) o;
        return idRez == that.idRez && idP == that.idP && idU == that.idU && idH == that.idH && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRez, idP, idU, idH, data);
    }

    public Pokoje getPokojeByIdP() {
        return pokojeByIdP;
    }

    public void setPokojeByIdP(Pokoje pokojeByIdP) {
        this.pokojeByIdP = pokojeByIdP;
    }

    public Uzytkownicy getUzytkownicyByIdU() {
        return uzytkownicyByIdU;
    }

    public void setUzytkownicyByIdU(Uzytkownicy uzytkownicyByIdU) {
        this.uzytkownicyByIdU = uzytkownicyByIdU;
    }

    public Godziny getGodzinyByIdH() {
        return godzinyByIdH;
    }

    public void setGodzinyByIdH(Godziny godzinyByIdH) {
        this.godzinyByIdH = godzinyByIdH;
    }
}
