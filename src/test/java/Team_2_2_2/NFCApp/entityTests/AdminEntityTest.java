package Team_2_2_2.NFCApp.entityTests;

import Team_2_2_2.NFCApp.entities.AdminEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminEntityTest {

    @Test
    void testAdminEntitySettersAndGetters() {
        String username = "admin";
        String password = "password123";
        Long adminId = 1L;

        // Create instance of AdminEntity
        AdminEntity admin = new AdminEntity(username, password);
        admin.setAdminId(adminId);  // Test setter

        // Test getters
        assertEquals(username, admin.getUsername(), "Username must be equal to the value set by constructor or setter.");
        assertEquals(password, admin.getPassword(), "Password must be equal to the value set by constructor or setter.");
        assertEquals(adminId, admin.getAdminId(), "Admin ID must be equal to the value set by setter.");
    }

    @Test
    void testAdminEntityDefaultConstructor() {
        AdminEntity admin = new AdminEntity();
        assertNull(admin.getUsername(), "Username should be null for a newly created admin object via default constructor.");
        assertNull(admin.getPassword(), "Password should be null for a newly created admin object via default constructor.");
        assertNull(admin.getAdminId(), "Admin ID should be null for a newly created admin object via default constructor.");
    }
}
