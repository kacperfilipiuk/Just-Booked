package com.example.mainApp.projekt_z_javy.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Wiadomosci {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_w")
    private int idW;
    @Basic
    @Column(name = "id_u")
    private int idU;
    @Basic
    @Column(name = "wiadomosc")
    private String wiadomosc;
    @ManyToOne
    @JoinColumn(name = "id_u", referencedColumnName = "id_u", nullable = false, insertable = false, updatable = false)
    private Uzytkownicy uzytkownicyByIdU;

    public int getIdW() {
        return idW;
    }

    public void setIdW(int idW) {
        this.idW = idW;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getWiadomosc() {
        return wiadomosc;
    }

    public void setWiadomosc(String wiadomosc) {
        this.wiadomosc = wiadomosc;
    }

    public Wiadomosci() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wiadomosci that = (Wiadomosci) o;
        return idW == that.idW && idU == that.idU && Objects.equals(wiadomosc, that.wiadomosc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idW, idU, wiadomosc);
    }

    public Uzytkownicy getUzytkownicyByIdU() {
        return uzytkownicyByIdU;
    }

    public void setUzytkownicyByIdU(Uzytkownicy uzytkownicyByIdU) {
        this.uzytkownicyByIdU = uzytkownicyByIdU;
    }
}
