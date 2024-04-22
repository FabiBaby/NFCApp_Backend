package Team_2_2_2.NFCApp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "admin")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(nullable = false)
    private String password;

    public AdminEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AdminEntity() {

    }

    //setters
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //getters
    public Long getAdminId() {
        return adminId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
