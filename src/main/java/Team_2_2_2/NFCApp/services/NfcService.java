package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.entities.NfcEntity;
import Team_2_2_2.NFCApp.repositories.NfcRepository;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NfcService {
    private final NfcRepository nfcRepository;

    @Autowired
    public NfcService(NfcRepository nfcRepository, ObjectRepository objectRepository) {
        this.nfcRepository = nfcRepository;
    }

    public NfcEntity addNfc(String nfcId) {
        if(nfcRepository.existsById(nfcId)) {
            return nfcRepository.findByNfcId(nfcId);
        }

        NfcEntity nfcEntity = new NfcEntity(nfcId);
        return nfcRepository.saveAndFlush(nfcEntity);
    }
}
