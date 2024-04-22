package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.controllers.AdminController;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ObjectService {
    private final ObjectRepository objectRepository;
    private final NfcService nfcService;

    @Autowired
    public ObjectService(ObjectRepository objectRepository, NfcService nfcService) {
        this.objectRepository = objectRepository;
        this.nfcService = nfcService;
    }

    @Transactional
    public ObjectEntity addObject(String objectName, String objectDesc, String objectLocation, String nfcId) {
        ObjectEntity newObject = new ObjectEntity(objectName, objectDesc, objectLocation, nfcId);
        return objectRepository.saveAndFlush(newObject);
    }

    public void removeObject(Long objectId){
        ObjectEntity objectEntity = objectRepository.findByObjectId(objectId);
        objectRepository.delete(objectEntity);
    }

    public ObjectEntity getObjectInfoByNfcId(String NfcId) {

        return objectRepository.findByNfcId(NfcId);
    }

    public List<ObjectEntity> getAllObjects() {
        return objectRepository.findAll();
    }
}
