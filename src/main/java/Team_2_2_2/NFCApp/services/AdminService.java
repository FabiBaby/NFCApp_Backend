package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.entities.AdminEntity;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a Spring service, making it eligible for Spring's dependency injection and transaction management
public class AdminService {
    private final AdminRepository adminRepository; // Dependency injection for AdminRepository
    private final ObjectService objectService; // Dependency injection for ObjectService

    @Autowired // Constructor-based dependency injection of AdminRepository & ObjectService
    public AdminService(AdminRepository adminRepository, ObjectService objectService) {
        this.adminRepository = adminRepository;
        this.objectService = objectService;
    }

    // Will log in admin if the password entered matches the password stored in the database
    public boolean loginAdmin(String username, String password) {
        try {
            AdminEntity adminEntity = adminRepository.findByUsername(username);
            if (adminEntity != null && password.equals(adminEntity.getPassword())) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    public ObjectEntity addObject(String objectName, String objectDesc, String objectLocation, String nfcId, Long adminId){
        return objectService.addObject(objectName, objectDesc, objectLocation, nfcId, adminId); // Will pass the parameters to the object service
    }

    public void removeObject(Long objectId){
        objectService.removeObject(objectId);
    }

    public List<ObjectEntity> getAllObjects() {
        return objectService.getAllObjects();
    }

    public Long getAdminId(String username){
        return adminRepository.findByUsername(username).getAdminId();
    }
}

