package Team_2_2_2.NFCApp.repositories;

import Team_2_2_2.NFCApp.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    //Find an Admin entity by its ID
    AdminEntity findByAdminId(int AdminId);

    AdminEntity findByUsername(String username);
}
