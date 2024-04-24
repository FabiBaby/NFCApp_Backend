package Team_2_2_2.NFCApp.entityTests;

import Team_2_2_2.NFCApp.entities.ObjectEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ObjectEntityTest {

    @Test
    void testObjectEntitySettersAndGetters() {
        // Sample data
        Long objectId = 1L;
        String objectName = "Interactive Map";
        String objectDesc = "An interactive map of the city.";
        String objectLocation = "Lobby";
        String nfcId = "NFC001";
        Long adminId = 10L;

        // Create an instance of ObjectEntity
        ObjectEntity object = new ObjectEntity(objectName, objectDesc, objectLocation, nfcId, adminId);
        object.setObjectId(objectId);  // Testing the setter

        // Asserts to verify getters and setters
        assertEquals(objectId, object.getObjectId(), "Object ID must match the set value.");
        assertEquals(objectName, object.getObjectName(), "Object name must match the set value.");
        assertEquals(objectDesc, object.getObjectDesc(), "Object description must match the set value.");
        assertEquals(objectLocation, object.getObjectLocation(), "Object location must match the set value.");
        assertEquals(nfcId, object.getNfcId(), "NFC ID must match the set value.");
        assertEquals(adminId, object.getAdminId(), "Admin ID must match the set value.");
    }

    @Test
    void testObjectEntityDefaultConstructor() {
        // Create an instance using the default constructor
        ObjectEntity object = new ObjectEntity();

        // Verify all fields are initialized to null or default
        assertNull(object.getObjectId(), "Object ID should be null by default.");
        assertNull(object.getObjectName(), "Object name should be null by default.");
        assertNull(object.getObjectDesc(), "Object description should be null by default.");
        assertNull(object.getObjectLocation(), "Object location should be null by default.");
        assertNull(object.getNfcId(), "NFC ID should be null by default.");
        assertNull(object.getAdminId(), "Admin ID should be null by default.");
    }
}
