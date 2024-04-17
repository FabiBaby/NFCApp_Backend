package Team_2_2_2.NFCApp.services;

import Team_2_2_2.NFCApp.controllers.AdminController;
import Team_2_2_2.NFCApp.entities.AdminEntity;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ObjectService objectService;

    @Autowired
    public AdminService(AdminRepository adminRepository, ObjectService objectService) {
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.objectService = objectService;
    }

    //Registers admin by encrypting the password and then saves the admin to the database
    public AdminEntity registerAdmin(String username, String password) {
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        AdminEntity newAdmin = new AdminEntity(username, encodedPassword);
        //Saves the new Admin to the database
        return adminRepository.saveAndFlush(newAdmin);
    }

    //Logins if the username exists and the entered password matches the one in the database
    public boolean loginAdmin(String username, String password) {
        try {
            AdminEntity adminEntity = adminRepository.findByUsername(username);
            if (adminEntity != null && password.equals(adminEntity.getPassword())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            // Log the exception
            System.out.println(e);
            return false;  // Consider how you want to handle errors - false may not always be appropriate
        }
    }

    public ObjectEntity addObject(String objectName, String objectDesc, String objectLocation){
        return objectService.addObject(objectName, objectDesc, objectLocation);
    }

    public ObjectEntity assignNfc(AdminController.ObjectDto objectDto, String nfcId){
        return objectService.assignNfc(objectDto, nfcId);
    }

    public void removeObject(Long objectId){
        objectService.removeObject(objectId);
    }

    public List<ObjectEntity> getAllObjects() {
        return objectService.getAllObjects();
    }
}

