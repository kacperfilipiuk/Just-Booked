package com.example.mainApp.projekt_z_javy.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Pokoje {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_p")
    private int idP;
    @Basic
    @Column(name = "nazwa")
    private String nazwa;
    @OneToMany(mappedBy = "pokojeByIdP")
    private Collection<Rezerwacje> rezerwacjesByIdP;

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Pokoje() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokoje pokoje = (Pokoje) o;
        return idP == pokoje.idP && Objects.equals(nazwa, pokoje.nazwa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idP, nazwa);
    }

    public Collection<Rezerwacje> getRezerwacjesByIdP() {
        return rezerwacjesByIdP;
    }

    public void setRezerwacjesByIdP(Collection<Rezerwacje> rezerwacjesByIdP) {
        this.rezerwacjesByIdP = rezerwacjesByIdP;
    }
}
