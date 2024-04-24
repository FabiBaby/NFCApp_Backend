package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // Marks this class as a Spring service, making it eligible for Spring's dependency injection and transaction management
public class ObjectService {
    private final ObjectRepository objectRepository; // Dependency injection for ObjectRepository

    @Autowired // Constructor-based dependency injection of ObjectRepository
    public ObjectService(ObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    @Transactional // Ensures the method is executed within a transactional context, rolling back in case of exceptions
    public ObjectEntity addObject(String objectName, String objectDesc, String objectLocation, String nfcId, Long adminId) {
        ObjectEntity newObject = new ObjectEntity(objectName, objectDesc, objectLocation, nfcId, adminId);
        return objectRepository.saveAndFlush(newObject); // Saves the new object entity and immediately commits to the database
    }

    public void removeObject(Long objectId){
        ObjectEntity objectEntity = objectRepository.findByObjectId(objectId);
        objectRepository.delete(objectEntity); // Removes the object entity from the database
    }

    public ObjectEntity getObjectInfoByNfcId(String NfcId) {
        return objectRepository.findByNfcId(NfcId); // Finds the object entity by NFC ID
    }

    public List<ObjectEntity> getAllObjects() {
        return objectRepository.findAll(); // Returns all object entities in the database
    }
}
