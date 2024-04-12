package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.entities.NfcEntity;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.NfcRepository;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NfcService {
    private final NfcRepository nfcRepository;
    private final ObjectRepository objectRepository;

    @Autowired
    public NfcService(NfcRepository nfcRepository, ObjectRepository objectRepository) {
        this.nfcRepository = nfcRepository;
        this.objectRepository = objectRepository;
    }

    public NfcEntity assignNfc(NfcEntity nfcEntity, Long objectId) {
        try{
            ObjectEntity objectEntity = objectRepository.getReferenceById(objectId);
            nfcEntity.setObjectEntity(objectEntity);
        }
        catch(EntityNotFoundException e){
            System.out.println("NFC Not Found");
        }

        return nfcRepository.saveAndFlush(nfcEntity);
    }

    @Transactional
    public NfcEntity unassignNfc(Long nfcId) {
        // Find the NFC entity by ID
        NfcEntity nfcEntity = nfcRepository.findById(nfcId)
                .orElseThrow(() -> new EntityNotFoundException("NFC not found with id: " + nfcId));

        // Unassign the object from the NFC entity
        nfcEntity.setObjectEntity(null);

        // Save the updated NFC entity
        return nfcRepository.saveAndFlush(nfcEntity);
    }
}
