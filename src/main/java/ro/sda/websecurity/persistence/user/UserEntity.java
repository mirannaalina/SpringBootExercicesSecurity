package ro.sda.websecurity.persistence.user;

import javax.persistence.*;

//JPA defineste niste standarde( adnotari)
//hibernate stie ce sa faca cu adnotarile alea

@Entity
@Table(name= "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name ="username")
    private String username;

    @Column(name ="password")
    private String password;

    @Column(name ="role")
    private String role;

    @Column(name= "age")
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
