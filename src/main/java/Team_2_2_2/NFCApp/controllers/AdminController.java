package Team_2_2_2.NFCApp.controllers;

import Team_2_2_2.NFCApp.entities.AdminEntity;
import Team_2_2_2.NFCApp.entities.NfcEntity;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //Controller method to register the admin to the database
    @PostMapping("/register")
    public ResponseEntity<AdminEntity> registerAdmin(@RequestParam String username, @RequestParam String password){
        AdminEntity adminEntity = adminService.registerAdmin(username, password);

        return ResponseEntity.ok(adminEntity);
    }

    //Controller method to verify if password matches username entered
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
    public ResponseEntity<ObjectEntity> addObject(@RequestBody ObjectDto ObjectDto){
        ObjectEntity objectEntity = adminService.addObject(ObjectDto.getObjectName(), ObjectDto.getObjectDesc(), ObjectDto.getObjectLocation());
        if(objectEntity != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(objectEntity);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/assignNfc")
    public ResponseEntity<ObjectEntity> assignNfc(@RequestBody AssignNfcRequest assignNfcRequest){
        ObjectEntity assignedObject = adminService.assignNfc(assignNfcRequest.getObjectEntity(), assignNfcRequest.getNfcUid());
        return ResponseEntity.status(HttpStatus.CREATED).body(assignedObject);
//        if(assignedObject != null){
//            return ResponseEntity.status(HttpStatus.CREATED).body(assignedObject);
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
    }

    @DeleteMapping("/removeObject")
    public ResponseEntity<String> removeObject(@RequestBody ObjectDto objectDto){
        adminService.removeObject(objectDto.getObjectId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Remove Successful");
    }

    @GetMapping("/getAllObjects")
    public ResponseEntity<List<ObjectEntity>> getAllObjects(){
        List<ObjectEntity> allObjectEntities = adminService.getAllObjects();
        return ResponseEntity.status(HttpStatus.OK).body(allObjectEntities);
    }

    public static class AssignNfcRequest {
        private ObjectDto objectDto;
        private String nfcUID;

        // Constructors
        public AssignNfcRequest(ObjectDto objectDto, String nfcUid) {
            this.objectDto = objectDto;
            this.nfcUID = nfcUID;
        }

        // Getters and setters
        public ObjectDto getObjectEntity() {
            return objectDto;
        }

        public void setObjectEntity(ObjectDto objectDto) {
            this.objectDto = objectDto;
        }

        public String getNfcUid() {
            return nfcUID;
        }

        public void setNfcUid(String nfcUID) {
            this.nfcUID = nfcUID;
        }
    }

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

        public void setNfcId(String nfcId) {
            NfcId = nfcId;
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