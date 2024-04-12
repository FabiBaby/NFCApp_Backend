package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.entities.NfcEntity;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.NfcRepository;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NfcService {
    private final NfcRepository nfcRepository;
    private final ObjectRepository objectRepository;

    @Autowired
    public NfcService(NfcRepository nfcRepository, ObjectRepository objectRepository) {
        this.nfcRepository = nfcRepository;
        this.objectRepository = objectRepository;
    }

    public void addNfc(Long nfcId) {
        if(nfcRepository.existsById(nfcId)) {
            return;
        }

        NfcEntity nfcEntity = new NfcEntity(nfcId);
        nfcRepository.saveAndFlush(nfcEntity);
    }
}
