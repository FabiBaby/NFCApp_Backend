package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectService {
    private final ObjectRepository objectRepository;

    @Autowired
    public ObjectService(ObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    public ObjectEntity updateObject(ObjectEntity objectEntity) {
        objectRepository.saveAndFlush(objectEntity);
        return objectEntity;
    }

    public ObjectEntity getObjectInfo(Long id) {
        return objectRepository.getReferenceById(id);
    }
}
