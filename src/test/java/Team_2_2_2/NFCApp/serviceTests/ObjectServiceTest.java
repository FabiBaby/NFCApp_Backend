package Team_2_2_2.NFCApp.serviceTests;

import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Team_2_2_2.NFCApp.services.ObjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class ObjectServiceTest {

    @Mock
    private ObjectRepository objectRepository;

    @InjectMocks
    private ObjectService objectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addObjectShouldSaveNewObject() {
        // Given
        String objectName = "ObjectName";
        String objectDesc = "ObjectDescription";
        String objectLocation = "ObjectLocation";
        String nfcId = "NFC123";
        Long adminId = 1L;
        ObjectEntity objectEntity = new ObjectEntity(objectName, objectDesc, objectLocation, nfcId, adminId);

        // When
        when(objectRepository.saveAndFlush(any(ObjectEntity.class))).thenReturn(objectEntity);

        // Then
        ObjectEntity savedEntity = objectService.addObject(objectName, objectDesc, objectLocation, nfcId, adminId);
        assertNotNull(savedEntity, "Saved entity should not be null");
        assertEquals(objectName, savedEntity.getObjectName(), "Saved entity should have the same object name");
        assertEquals(objectDesc, savedEntity.getObjectDesc(), "Saved entity should have the same object description");
        assertEquals(objectLocation, savedEntity.getObjectLocation(), "Saved entity should have the same object location");
        assertEquals(nfcId, savedEntity.getNfcId(), "Saved entity should have the same NFC ID");
        assertEquals(adminId, savedEntity.getAdminId(), "Saved entity should have the same admin ID");

        // Verify interactions
        verify(objectRepository).saveAndFlush(any(ObjectEntity.class));
    }

    @Test
    void removeObjectShouldRemoveTheObject() {
        Long objectId = 1L;
        ObjectEntity objectEntity = new ObjectEntity();
        objectEntity.setObjectId(objectId);

        when(objectRepository.findByObjectId(objectId)).thenReturn(objectEntity);

        objectService.removeObject(objectId);

        verify(objectRepository).delete(objectEntity);
    }

    @Test
    void getObjectInfoByNfcIdShouldReturnObject() {
        String nfcId = "NFC123";
        ObjectEntity expectedObject = new ObjectEntity();
        expectedObject.setNfcId(nfcId);

        when(objectRepository.findByNfcId(nfcId)).thenReturn(expectedObject);

        ObjectEntity result = objectService.getObjectInfoByNfcId(nfcId);

        assertNotNull(result);
        assertEquals(expectedObject, result);
        verify(objectRepository).findByNfcId(nfcId);
    }

    @Test
    void getAllObjectsShouldReturnAllObjects() {
        ObjectEntity object1 = new ObjectEntity();
        ObjectEntity object2 = new ObjectEntity();
        List<ObjectEntity> expectedObjects = Arrays.asList(object1, object2);

        when(objectRepository.findAll()).thenReturn(expectedObjects);

        List<ObjectEntity> results = objectService.getAllObjects();

        assertNotNull(results);
        assertEquals(expectedObjects.size(), results.size());
        assertIterableEquals(expectedObjects, results);
        verify(objectRepository).findAll();
    }
}