package Team_2_2_2.NFCApp.entities;

import jakarta.persistence.*;
import org.springframework.lang.Nullable;

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

//    Foreign keys go here
    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(nullable = true, name = "nfcId")
    private NfcEntity nfc;

    @ManyToOne(fetch = FetchType.EAGER)
    private AdminEntity admin;

    private String nfcId;

    public ObjectEntity(String objectName, String objectDesc, String objectLocation, String nfcId) {
        setObjectName(objectName);
        setObjectDesc(objectDesc);
        setObjectLocation(objectLocation);
        setNfcId(nfcId);
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

//    public void setNfc(NfcEntity nfc) {
//        this.nfc = nfc;
//    }
    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

    public String getNfcId() {
        return nfcId;
    }

    public void setAdmin(AdminEntity admin) {
        this.admin = admin;
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

//    public NfcEntity getNfc() {
//        return nfc;
//    }
//
//    public String getNfcId() {
//        if (this.nfc != null) {
//            return this.nfc.getNfcId();
//        } else {
//            return null;
//        }
//    }

    public AdminEntity getAdmin() {
        return admin;
    }
}
