package Team_2_2_2.NFCApp.controllers;

import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import Team_2_2_2.NFCApp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ObjectRepository objectRepository;
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService, ObjectRepository objectRepository) {
        this.adminService = adminService;
        this.objectRepository = objectRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody LoginDto loginDto){
        if(adminService.loginAdmin(loginDto.getUsername(), loginDto.getPassword())){
            return ResponseEntity.ok(new LoginResponse(true, "Login Successful"));
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(false, "Login Failed"));
        }
    }

    @PostMapping("/addObject")
    public ResponseEntity<ObjectEntity> addObject(@RequestBody ObjectDto objectDto){
        // If an object already exists in the database then it returns a conflict message
        if(objectRepository.findByNfcId(objectDto.getNfcId()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        ObjectEntity objectEntity = adminService.addObject(objectDto.getObjectName(), objectDto.getObjectDesc(),
                objectDto.getObjectLocation(), objectDto.getNfcId());

        if (objectEntity != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(objectEntity);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/removeObject")
    public ResponseEntity<String> removeObject(@RequestParam("objectId") Long objectId){
        adminService.removeObject(objectId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Remove Successful");
    }

    @GetMapping("/getAllObjects")
    public ResponseEntity<List<ObjectEntity>> getAllObjects(){
        List<ObjectEntity> allObjectEntities = adminService.getAllObjects();
        return ResponseEntity.status(HttpStatus.OK).body(allObjectEntities);
    }

//    @GetMapping("/getAdminInfo")
//    public ResponseEntity<AdminEntity> getAdminInfo(){
//        return adminService.getAdminInfo();
//    }

    public static class ObjectDto {
        private String objectName;
        private String objectDesc;
        private String objectLocation;
        private String NfcId;
        private Long objectId;

        public String getObjectName() {
            return objectName;
        }

        public String getObjectDesc() {
            return objectDesc;
        }

        public String getObjectLocation() {
            return objectLocation;
        }

        public Long getObjectId() {
            return objectId;
        }

        public String getNfcId() {
            return NfcId;
        }
    }

    public static class LoginDto {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class AdminDto {
        private Long adminId;
        private String username;
        private String password;

        public AdminDto(String username, String password) {
            this.username = username;
            this.password = password;
        }

        // getters and setters
        public Long getAdminId() {
            return adminId;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class LoginResponse {
        private boolean success;
        private String message;

        public LoginResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        // Getters and setters
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}