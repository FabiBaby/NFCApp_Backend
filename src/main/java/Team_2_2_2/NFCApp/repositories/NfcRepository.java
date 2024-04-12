package Team_2_2_2.NFCApp.repositories;

import Team_2_2_2.NFCApp.entities.NfcEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NfcRepository extends JpaRepository<NfcEntity, Long> {
    boolean existsById(String nfcId);
}
