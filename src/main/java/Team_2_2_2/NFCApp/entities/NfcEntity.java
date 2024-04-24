package Team_2_2_2.NFCApp.entities;

import jakarta.persistence.*;

@Entity  // Specifies that this class is an entity and is mapped to a database table
@Table(name = "nfc")  // Specifies the table in the database with which this entity is associated
public class NfcEntity {
    @Id  // Marks this field as the primary key of the entity
    private String nfcId;

    // Default constructor required for JPA
    public NfcEntity() {

    }

    // Constructor to initialize the NfcEntity with an NFC ID
    public NfcEntity(String nfcId) {
        setNfcId(nfcId);  // Calls the setter to ensure any business logic in setter is executed
    }

    // Setter for NFC ID, encapsulates the logic for setting the NFC ID
    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

    // Getter for NFC ID, retrieves the NFC ID
    public String getNfcId() {
        return nfcId;
    }
}

