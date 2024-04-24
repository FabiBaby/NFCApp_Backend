package Team_2_2_2.NFCApp.controllers;

import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.repositories.ObjectRepository;
import Team_2_2_2.NFCApp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Marks this class as a controller where every method returns a domain object instead of a view
@RequestMapping("/admin")  // Maps HTTP requests to /admin path
public class AdminController {
    private final ObjectRepository objectRepository;  // Repository for accessing object data
    private AdminService adminService;  // Service layer dependency for admin related operations

    @Autowired  // Injects AdminService and ObjectRepository dependencies
    public AdminController(AdminService adminService, ObjectRepository objectRepository) {
        this.adminService = adminService;
        this.objectRepository = objectRepository;
    }

    @PostMapping("/login")  // Maps POST requests to /login URL for logging in an admin
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody LoginDto loginDto) {
        if (adminService.loginAdmin(loginDto.getUsername(), loginDto.getPassword())) {
            return ResponseEntity.ok(new LoginResponse(true, "Login Successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(false, "Login Failed"));
        }
    }

    @PostMapping("/addObject")  // Maps POST requests to /addObject URL for adding a new object
    public ResponseEntity<ObjectEntity> addObject(@RequestBody ObjectDto objectDto) {
        if (objectRepository.findByNfcId(objectDto.getNfcId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);  // Conflict if object with NFC ID exists
        }
        ObjectEntity objectEntity = adminService.addObject(objectDto.getObjectName(), objectDto.getObjectDesc(),
                objectDto.getObjectLocation(), objectDto.getNfcId(), objectDto.getAdminId());
        if (objectEntity != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(objectEntity);  // Return created object
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Bad request if creation failed
        }
    }

    @DeleteMapping("/removeObject")  // Maps DELETE requests to /removeObject URL for removing an object
    public ResponseEntity<String> removeObject(@RequestParam("objectId") Long objectId) {
        adminService.removeObject(objectId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Remove Successful");
    }

    @GetMapping("/getAllObjects")  // Maps GET requests to /getAllObjects URL to fetch all objects
    public ResponseEntity<List<ObjectEntity>> getAllObjects() {
        List<ObjectEntity> allObjectEntities = adminService.getAllObjects();
        return ResponseEntity.status(HttpStatus.OK).body(allObjectEntities);
    }

    @GetMapping("/getAdminId")  // Maps GET requests to /getAdminId URL to fetch admin ID based on username
    public Long getAdminId(@RequestParam("username") String username) {
        return adminService.getAdminId(username);
    }

    // DTO and response classes with getters and setters
    public static class ObjectDto {
        private String objectName;
        private String objectDesc;
        private String objectLocation;
        private String NfcId;
        private Long objectId;
        private Long adminId;

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

        public Long getAdminId() {
            return adminId;
        }

        public void setNfcId(String NfcId) {
            this.NfcId = NfcId;
        }

        public void setAdminId(Long adminId) {
            this.adminId = adminId;
        }
    }

    public static class LoginDto {
        private String username;
        private String password;

        // getters and setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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