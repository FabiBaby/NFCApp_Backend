package Team_2_2_2.NFCApp.entities;

import jakarta.persistence.*;

@Entity  // Declares the class as an entity class that can be mapped to a database table
@Table(name = "object")  // Specifies the name of the table in the database for this entity
public class ObjectEntity {
    @Id  // Designates this field as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Configures the strategy for auto-generating the primary key value
    private Long objectId;

    @Column(nullable = false)  // Specifies that the objectName column cannot contain null values
    private String objectName;

    @Column(nullable = false)  // Specifies that the objectDesc column cannot contain null values
    private String objectDesc;

    @Column(nullable = false)  // Specifies that the objectLocation column cannot contain null values
    private String objectLocation;  // Location of the object

    // Foreign key to link to the NFC entity
    private String nfcId;  // The NFC identifier associated with this object

    // Foreign key to link to the Admin entity
    private Long adminId;  // The admin identifier associated with this object

    // Constructor to initialize all fields with values
    public ObjectEntity(String objectName, String objectDesc, String objectLocation, String nfcId, Long adminId) {
        setObjectName(objectName);
        setObjectDesc(objectDesc);
        setObjectLocation(objectLocation);
        setNfcId(nfcId);
        setAdminId(adminId);
    }

    // Default constructor necessary for JPA
    public ObjectEntity() {

    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public void setObjectDesc(String objectDesc) {
        this.objectDesc = objectDesc;
    }

    public void setObjectLocation(String objectLocation) {
        this.objectLocation = objectLocation;
    }

    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getObjectDesc() {
        return objectDesc;
    }

    public String getObjectLocation() {
        return objectLocation;
    }

    public String getNfcId() {
        return nfcId;
    }

    public Long getAdminId() {
        return adminId;
    }
}
