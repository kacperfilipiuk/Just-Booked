package pl.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uzytkownicy")
public class Users {
    @Id
    @Column(name="id_u")
    private Integer id_u;
    @Column(name="login")
    private String login;
    @Column(name="haslo")
    private String haslo;
    @Column(name="email")
    private String email;

    public void ShowUser(int id_u, String login){
        //super();
        this.id_u=id_u;
        this.login=login;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
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
        return "Users{" +
                "id_u=" + id_u +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
