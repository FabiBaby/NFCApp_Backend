package Team_2_2_2.NFCApp.serviceTests;

import Team_2_2_2.NFCApp.entities.NfcEntity;
import Team_2_2_2.NFCApp.repositories.NfcRepository;
import static org.mockito.Mockito.*;

import Team_2_2_2.NFCApp.services.NfcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class NfcServiceTest {

    @Mock
    private NfcRepository nfcRepository;

    @InjectMocks
    private NfcService nfcService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addNfcShouldSaveNewNfcWhenItDoesNotExist() {
        String nfcId = "NFC123";

        // Mock the behavior of the nfcRepository to simulate the NFC ID not existing
        when(nfcRepository.existsById(nfcId)).thenReturn(false);

        // Call the method to be tested
        nfcService.addNfc(nfcId);

        // Verify that nfcRepository.saveAndFlush was called with a new NfcEntity
        verify(nfcRepository).saveAndFlush(any(NfcEntity.class));
    }

    @Test
    void addNfcShouldNotSaveNfcWhenItAlreadyExists() {
        String nfcId = "NFC123";

        // Mock the behavior of the nfcRepository to simulate the NFC ID already existing
        when(nfcRepository.existsById(nfcId)).thenReturn(true);

        // Call the method to be tested
        nfcService.addNfc(nfcId);

        // Verify that nfcRepository.saveAndFlush was never called
        verify(nfcRepository, never()).saveAndFlush(any(NfcEntity.class));
    }
}
