package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.entities.NfcEntity;
import Team_2_2_2.NFCApp.repositories.NfcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Marks this class as a Spring service, making it eligible for Spring's dependency injection and transaction management
public class NfcService {
    private final NfcRepository nfcRepository; // Dependency injection for NfcRepository

    @Autowired // Constructor-based dependency injection of NfcRepository
    public NfcService(NfcRepository nfcRepository) {
        this.nfcRepository = nfcRepository;
    }

    // Adds a new NFC to the database if it is not already there
    public void addNfc(String nfcId) {
        if(nfcRepository.existsById(nfcId)) {
            return;
        }

        NfcEntity nfcEntity = new NfcEntity(nfcId);
        nfcRepository.saveAndFlush(nfcEntity);
    }
}
