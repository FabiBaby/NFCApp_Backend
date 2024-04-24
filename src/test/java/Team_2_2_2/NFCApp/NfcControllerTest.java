package Team_2_2_2.NFCApp;

import Team_2_2_2.NFCApp.controllers.NfcController;
import Team_2_2_2.NFCApp.services.NfcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(NfcController.class)
public class NfcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NfcService nfcService;

    @Test // Test adding a new NFC tag
    public void testAddNfc() throws Exception {
        String nfcId = "NFC001";

        // Perform the POST request with the nfcId as a request parameter
        mockMvc.perform(post("/nfc/addNfc")
                        .param("nfcId", nfcId))  // Include the nfcId as a request parameter
                .andExpect(status().isOk())  // Expect a 200 OK response
                .andExpect(content().string("NFC added"));  // Check the response content

        // Verify that the nfcService.addNfc method was called exactly once with the provided nfcId
        verify(nfcService, times(1)).addNfc(nfcId);
    }
}
