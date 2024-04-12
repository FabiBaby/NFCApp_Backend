package Team_2_2_2.NFCApp.repositories;

import Team_2_2_2.NFCApp.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByUsername(String username);
}
