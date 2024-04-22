package Team_2_2_2.NFCApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "object")
public class ObjectEntity {
    //Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long objectId;

    //Other attributes for object table
    @Column(nullable = false)
    private String objectName;

    @Column(nullable = false)
    private String objectDesc;

    @Column(nullable = false)
    private String objectLocation;

    // Foreign keys
    private String nfcId;

    private Long adminId;

    public ObjectEntity(String objectName, String objectDesc, String objectLocation, String nfcId, Long adminId) {
        setObjectName(objectName);
        setObjectDesc(objectDesc);
        setObjectLocation(objectLocation);
        setNfcId(nfcId);
        setAdminId(adminId);
    }

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

    public String getNfcId() {
        return nfcId;
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

    public Long getAdminId() {
        return adminId;
    }
}
