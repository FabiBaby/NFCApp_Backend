package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.entities.NfcEntity;
import Team_2_2_2.NFCApp.repositories.NfcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NfcService {
    private final NfcRepository nfcRepository;

    @Autowired
    public NfcService(NfcRepository nfcRepository) {
        this.nfcRepository = nfcRepository;
    }

    public void addNfc(String nfcId) {
        if(nfcRepository.existsById(nfcId)) {
            return;
        }

        NfcEntity nfcEntity = new NfcEntity(nfcId);
        nfcRepository.saveAndFlush(nfcEntity);
    }
}
