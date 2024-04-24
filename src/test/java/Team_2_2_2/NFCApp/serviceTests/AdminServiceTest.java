package Team_2_2_2.NFCApp.serviceTests;

import Team_2_2_2.NFCApp.entities.AdminEntity;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.AdminRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Team_2_2_2.NFCApp.services.AdminService;
import Team_2_2_2.NFCApp.services.ObjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private ObjectService objectService;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginAdminShouldReturnTrueForValidCredentials() {
        String username = "admin";
        String password = "password";
        AdminEntity adminEntity = new AdminEntity(username, password);

        when(adminRepository.findByUsername(username)).thenReturn(adminEntity);

        boolean result = adminService.loginAdmin(username, password);

        assertTrue(result, "The method should return true for valid credentials.");
        verify(adminRepository).findByUsername(username);
    }

    @Test
    void loginAdminShouldReturnFalseForInvalidCredentials() {
        String username = "admin";
        String password = "password";
        AdminEntity adminEntity = new AdminEntity(username, "differentPassword");

        when(adminRepository.findByUsername(username)).thenReturn(adminEntity);

        boolean result = adminService.loginAdmin(username, password);

        assertFalse(result, "The method should return false for invalid credentials.");
        verify(adminRepository).findByUsername(username);
    }

    @Test
    void addObjectShouldDelegateToObjectService() {
        String objectName = "Object Name";
        String objectDesc = "Object Description";
        String objectLocation = "Object Location";
        String nfcId = "NFC123";
        Long adminId = 1L;
        ObjectEntity mockObjectEntity = new ObjectEntity(objectName, objectDesc, objectLocation, nfcId, adminId);

        // Set up mock behavior
        when(objectService.addObject(objectName, objectDesc, objectLocation, nfcId, adminId)).thenReturn(mockObjectEntity);

        // Execute the method to be tested
        ObjectEntity resultEntity = adminService.addObject(objectName, objectDesc, objectLocation, nfcId, adminId);

        // Assertions
        assertNotNull(resultEntity, "Returned ObjectEntity should not be null");
        assertSame(mockObjectEntity, resultEntity, "The returned ObjectEntity should be the one returned by the ObjectService");

        // Verify interactions
        verify(objectService).addObject(objectName, objectDesc, objectLocation, nfcId, adminId);
    }

    @Test
    void removeObjectShouldDelegateToObjectService() {
        Long objectId = 1L;

        // Call the method to be tested
        adminService.removeObject(objectId);

        // Verify that the ObjectService's removeObject method was called with the correct id
        verify(objectService).removeObject(objectId);
    }

    @Test
    void getAllObjectsShouldDelegateToObjectService() {
        // Given
        List<ObjectEntity> objectList = new ArrayList<>();
        objectList.add(new ObjectEntity("Object1", "Description1", "Location1", "NFC001", 1L));
        objectList.add(new ObjectEntity("Object2", "Description2", "Location2", "NFC002", 2L));

        // When
        when(objectService.getAllObjects()).thenReturn(objectList);

        // Then
        List<ObjectEntity> result = adminService.getAllObjects();
        assertNotNull(result, "The returned list should not be null");
        assertEquals(2, result.size(), "The returned list should contain the correct number of elements");
        assertEquals(objectList, result, "The returned list should match the one returned by the ObjectService");
    }

    @Test
    void getAdminIdShouldReturnIdForGivenUsername() {
        // Given
        String username = "adminUser";
        AdminEntity admin = new AdminEntity(username, "password");
        admin.setAdminId(1L); // Assuming setter has been correctly implemented

        // When
        when(adminRepository.findByUsername(username)).thenReturn(admin);

        // Then
        Long result = adminService.getAdminId(username);
        assertNotNull(result, "The returned admin ID should not be null");
        assertEquals(1L, result, "The returned admin ID should match the expected value");
    }
}
