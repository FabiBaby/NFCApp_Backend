package Team_2_2_2.NFCApp.controllerTests;

import Team_2_2_2.NFCApp.controllers.ObjectController;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.services.ObjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ObjectController.class)
public class ObjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ObjectService objectService;

    @Test // Test retrieving object info by NFC ID successfully
    public void testGetObjectInfoByNfcIdSuccess() throws Exception {
        String nfcId = "NFC123";
        ObjectEntity objectEntity = new ObjectEntity("Object Name", "Description", "Location", nfcId, 1L);

        // Mock the behavior of objectService to return the objectEntity when the NFC ID is provided
        when(objectService.getObjectInfoByNfcId(nfcId)).thenReturn(objectEntity);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/objects/getObjectInfoByNfcId")
                        .param("nfcId", nfcId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.objectName", is("Object Name")))
                .andExpect(jsonPath("$.objectDesc", is("Description")))
                .andExpect(jsonPath("$.objectLocation", is("Location")))
                .andExpect(jsonPath("$.nfcId", is(nfcId)));
    }

    @Test // Test retrieving object info by NFC ID when no object is found
    public void testGetObjectInfoByNfcIdNotFound() throws Exception {
        String nfcId = "NFC999";  // Assume this NFC ID does not correspond to any object

        // Mock the behavior of objectService to return null when the NFC ID is not found
        when(objectService.getObjectInfoByNfcId(nfcId)).thenReturn(null);

        // Perform the GET request and verify the response is 404 Not Found
        mockMvc.perform(get("/admin/getObjectInfoByNfcId")
                        .param("nfcId", nfcId))
                .andExpect(status().isNotFound());
    }

}
