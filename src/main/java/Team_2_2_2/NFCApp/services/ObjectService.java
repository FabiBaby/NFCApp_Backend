package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.entities.NfcEntity;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ObjectService {
    private final ObjectRepository objectRepository;

    @Autowired
    public ObjectService(ObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    @Transactional
    public ObjectEntity addObject(String objectName, String objectDesc, String objectLocation) {
        ObjectEntity newObject = new ObjectEntity(objectName, objectDesc, objectLocation);
        return objectRepository.saveAndFlush(newObject);
    }

    @Transactional
    public ObjectEntity assignNfc(ObjectEntity objectEntity, NfcEntity nfcEntity) {
        objectEntity.setNfc(nfcEntity);

        return objectRepository.saveAndFlush(objectEntity);
    }

    public void removeObject(ObjectEntity objectEntity){
        objectRepository.delete(objectEntity);
    }

    public ObjectEntity getObjectInfoByNfcId(String NfcId) {
        return objectRepository.findByNfc_NfcId(NfcId);
    }
}
