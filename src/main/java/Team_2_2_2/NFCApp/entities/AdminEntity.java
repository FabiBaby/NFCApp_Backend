package Team_2_2_2.NFCApp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity  // Specifies that this class is an entity in the context of persistence
@Table(name = "admin")  // Specifies the table in the database with which this entity is associated
public class AdminEntity {
    @Id  // Marks this field as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Configures the way of increment of the specified column(field)
    private Long adminId;

    @Column(unique=true, nullable=false)  // Specifies that username must be unique and not null
    private String username;

    @Column(nullable = false)  // Specifies that the password cannot be null
    private String password;

    // Constructor with parameters to initialize the admin entity
    public AdminEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Default constructor required for JPA
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
