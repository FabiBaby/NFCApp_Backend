package Team_2_2_2.NFCApp;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Team_2_2_2.NFCApp.entities.AdminEntity;
import Team_2_2_2.NFCApp.repositories.AdminRepository;
import Team_2_2_2.NFCApp.services.AdminService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    public void testRegisterAdmin() {
        // Given
        String username = "admin";
        String password = "pass";
        when(adminRepository.saveAndFlush(any(AdminEntity.class))).thenReturn(new AdminEntity(username, "encrypted_password"));

        // When
        AdminEntity result = adminService.registerAdmin(username, password);

        // Then
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }
}