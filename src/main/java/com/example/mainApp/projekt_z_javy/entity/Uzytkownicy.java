package com.example.mainApp.projekt_z_javy.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Uzytkownicy {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_u")
    private int idU;
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "haslo")
    private String haslo;
    @Basic
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "uzytkownicyByIdU")
    private Collection<Rezerwacje> rezerwacjesByIdU;

    public Uzytkownicy() {
    }

    public Uzytkownicy(int idU, String login, String haslo, String email) {
        super();
        this.idU = idU;
        this.login = login;
        this.haslo = haslo;
        this.email = email;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Uzytkownicy{" +
                "idU=" + idU +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uzytkownicy that = (Uzytkownicy) o;
        return idU == that.idU && Objects.equals(login, that.login) && Objects.equals(haslo, that.haslo) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idU, login, haslo, email);
    }

    public Collection<Rezerwacje> getRezerwacjesByIdU() {
        return rezerwacjesByIdU;
    }

    public void setRezerwacjesByIdU(Collection<Rezerwacje> rezerwacjesByIdU) {
        this.rezerwacjesByIdU = rezerwacjesByIdU;
    }
}
