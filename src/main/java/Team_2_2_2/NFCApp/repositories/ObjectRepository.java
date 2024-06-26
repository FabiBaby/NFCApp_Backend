package Team_2_2_2.NFCApp.repositories;

import Team_2_2_2.NFCApp.entities.ObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectRepository extends JpaRepository<ObjectEntity, Long> {
    ObjectEntity findByObjectId(Long objectId);
    ObjectEntity findByNfcId(String nfcId);
}

