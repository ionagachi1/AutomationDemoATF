package persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;


    public int getId() {return id;}
    public String getUsername() { return username; }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstname;
    }
    public String getLastName() {
        return lastname;
    }
    public String getEmail() {
        return email;
    }

    ////////////////

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }
    public void setLastName(String lastName) {
        this.lastname = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}