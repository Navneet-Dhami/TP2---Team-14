package SoundwavesProject.Soundwaves;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Name")
    private String firstname;
    private String surname; 
    private String email; 
    private String password;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() { 
        return this.id;
    }

    public String getFirstname() { 
        return this.firstname;
    }

    public String getSurname() { 
        return this.surname;
    }

    public String getEmail() { 
        return this.email;
    }

    public String getPassword() { 
        return this.password;
    }

    public void setId(int id) { 
        this.id = id;
    }

    public void setFirstname(String firstname) { 
        this.firstname = firstname;
    }

    public void setSurname(String surname) { 
        this.surname = surname;
    }

    public void setEmail (String email) { 
        this.email = email;
    }

    public void setPassword(String password) { 
        this.password = password;
    }
}   