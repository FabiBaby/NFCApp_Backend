package Team_2_2_2.NFCApp.controllerTests;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import Team_2_2_2.NFCApp.controllers.AdminController;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import Team_2_2_2.NFCApp.services.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @MockBean
    private ObjectRepository objectRepository;

    @Test // Test successful login
    public void testLoginSuccessfully() throws Exception {
        when(adminService.loginAdmin("admin", "password")).thenReturn(true);

        mockMvc.perform(post("/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"admin\", \"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Login Successful")));
    }

    @Test // Test unsuccessful login
    public void testFailLogin() throws Exception {
        when(adminService.loginAdmin("admin", "wrongpassword")).thenReturn(false);

        mockMvc.perform(post("/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"admin\", \"password\": \"wrongpassword\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(containsString("Login Failed")));
    }

    @Test // Test adding a new object successfully
    public void testAddObject() throws Exception {
        // Mock the behavior of the repository and service
        when(objectRepository.findByNfcId("NFC123")).thenReturn(null);
        when(adminService.addObject("New Object", "Description", "Location", "NFC123", 1L))
                .thenReturn(new ObjectEntity("New Object", "Description", "Location", "NFC123", 1L));

        // Perform the request and check the response
        mockMvc.perform(post("/admin/addObject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"objectName\": \"New Object\", \"objectDesc\": \"Description\", \"objectLocation\": \"Location\", \"nfcId\": \"NFC123\", \"adminId\": 1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.objectName").value("New Object"))
                .andExpect(jsonPath("$.objectDesc").value("Description"))
                .andExpect(jsonPath("$.objectLocation").value("Location"))
                .andExpect(jsonPath("$.nfcId").value("NFC123"))
                .andExpect(jsonPath("$.adminId").value(1));
    }

    @Test // Test conflict when the object with NFC ID already exists
    public void testAddObjectConflict() throws Exception {
        when(objectRepository.findByNfcId("NFC123")).thenReturn(new ObjectEntity());

        mockMvc.perform(post("/admin/addObject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"objectName\": \"New Object\", \"objectDesc\": \"Description\", \"objectLocation\": \"Location\", \"nfcId\": \"NFC123\", \"adminId\": 1}"))
                .andExpect(status().isConflict());
    }

    @Test // Test bad request when object creation fails
    public void testAddObjectCreationFailure() throws Exception {
        when(objectRepository.findByNfcId("NFC123")).thenReturn(null);
        when(adminService.addObject(any(), any(), any(), any(), any())).thenReturn(null);

        mockMvc.perform(post("/admin/addObject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"objectName\": \"New Object\", \"objectDesc\": \"Description\", \"objectLocation\": \"Location\", \"nfcId\": \"NFC123\", \"adminId\": 1}"))
                .andExpect(status().isBadRequest());
    }

    @Test // Test removing an object successfully
    public void testRemoveObject() throws Exception {
        Long objectId = 1L;  // Example object ID

        mockMvc.perform(delete("/admin/removeObject")
                        .param("objectId", objectId.toString()))
                .andExpect(status().isCreated())  // Check if the response status is CREATED
                .andExpect(content().string("Remove Successful"));

        // Verify that adminService.removeObject was called exactly once with the specified object ID
        verify(adminService, times(1)).removeObject(objectId);
    }

    @Test // Test fetching all objects successfully
    public void testGetAllObjects() throws Exception {
        // Create sample data
        ObjectEntity object1 = new ObjectEntity("Object 1", "Description 1", "Location 1", "NFC001", 1L);
        ObjectEntity object2 = new ObjectEntity("Object 2", "Description 2", "Location 2", "NFC002", 2L);
        List<ObjectEntity> allObjectEntities = Arrays.asList(object1, object2);

        // Mock the behavior of adminService to return the sample list
        when(adminService.getAllObjects()).thenReturn(allObjectEntities);

        // Perform the request and verify the outcome
        mockMvc.perform(get("/admin/getAllObjects"))
                .andExpect(status().isOk())  // Verify that response status is OK
                .andExpect(jsonPath("$", hasSize(2)))  // Check if the array has the correct size
                .andExpect(jsonPath("$[0].objectName", is("Object 1")))
                .andExpect(jsonPath("$[0].nfcId", is("NFC001")))
                .andExpect(jsonPath("$[1].objectName", is("Object 2")))
                .andExpect(jsonPath("$[1].nfcId", is("NFC002")));
    }

    @Test // Test fetching admin ID by username
    public void testGetAdminId() throws Exception {
        // Set up the expected admin ID and the username
        String username = "adminUser";
        Long expectedAdminId = 42L;

        // Mock the adminService.getAdminId to return the expected admin ID
        when(adminService.getAdminId(username)).thenReturn(expectedAdminId);

        // Perform the GET request to fetch admin ID by username
        mockMvc.perform(get("/admin/getAdminId")
                        .param("username", username))  // Add the username as a request parameter
                .andExpect(status().isOk())  // Expect a 200 OK response
                .andExpect(content().string(String.valueOf(expectedAdminId)));  // Check that the response contains the expected admin ID
    }
}
