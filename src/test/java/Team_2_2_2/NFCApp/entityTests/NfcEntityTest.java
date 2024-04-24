package Team_2_2_2.NFCApp.entityTests;

import Team_2_2_2.NFCApp.entities.NfcEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NfcEntityTest {

    @Test
    void testNfcEntitySettersAndGetters() {
        String nfcId = "NFC10001";

        // Create an instance of NfcEntity
        NfcEntity nfcEntity = new NfcEntity(nfcId);

        // Test getter
        assertEquals(nfcId, nfcEntity.getNfcId(), "Getter for nfcId should return what was set by the constructor");

        // Test setter
        String newNfcId = "NFC10002";
        nfcEntity.setNfcId(newNfcId);
        assertEquals(newNfcId, nfcEntity.getNfcId(), "Setter for nfcId should update the value of nfcId");
    }

    @Test
    void testNfcEntityDefaultConstructor() {
        // Create an instance using the default constructor
        NfcEntity nfcEntity = new NfcEntity();

        // Verify that the NFC ID is initialized to null
        assertNull(nfcEntity.getNfcId(), "NFC ID should be null when initialized with the default constructor");
    }
}
