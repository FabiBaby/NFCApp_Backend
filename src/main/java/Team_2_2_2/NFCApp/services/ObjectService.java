package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.controllers.AdminController;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public ObjectEntity addObject(String objectName, String objectDesc, String objectLocation, String nfcId, Long adminId) {
        ObjectEntity newObject = new ObjectEntity(objectName, objectDesc, objectLocation, nfcId, adminId);
        return objectRepository.saveAndFlush(newObject);
    }

    private ObjectEntity convertDtoToEntity(AdminController.ObjectDto dto) {
        // Assuming you have a constructor or a method to create an entity from a DTO
        ObjectEntity entity = new ObjectEntity();
        entity.setObjectName(dto.getObjectName());
        entity.setObjectDesc(dto.getObjectDesc());
        entity.setObjectLocation(dto.getObjectLocation());
        // Set other fields as necessary
        return entity;
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
