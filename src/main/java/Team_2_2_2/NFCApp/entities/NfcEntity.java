package Team_2_2_2.NFCApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "nfc")
public class NfcEntity {
    @Id
    private String nfcId;

    public NfcEntity(){

    }

    public NfcEntity(String nfcId) {
        setNfcId(nfcId);
    }

    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

    public String getNfcId() {
        return nfcId;
    }
}
