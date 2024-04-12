package Team_2_2_2.NFCApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "nfc")
public class NfcEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nfcId;

    @OneToOne
    @JoinColumn(name = "objectId", referencedColumnName = "objectId")
    private ObjectEntity objectEntity;

    public void setNfcId(Long nfcId) {
        this.nfcId = nfcId;
    }

    public Long getNfcId() {
        return nfcId;
    }

    public void setObjectEntity(ObjectEntity objectEntity) {
        this.objectEntity = objectEntity;
    }

    public ObjectEntity getObjectEntity() {
        return objectEntity;
    }
}
