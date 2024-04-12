package Team_2_2_2.NFCApp.controllers;

import Team_2_2_2.NFCApp.entities.AdminEntity;
import Team_2_2_2.NFCApp.entities.NfcEntity;
import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> loginAdmin(@RequestParam String username, @RequestParam String password){
        if(adminService.loginAdmin(username, password)){
            return ResponseEntity.ok("Login Successful");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/addObject")
    public ResponseEntity<ObjectEntity> addObject(@RequestParam String objectName, @RequestParam String objectDesc,
                                      @RequestParam String objectLocation){
        ObjectEntity objectEntity = adminService.addObject(objectName, objectDesc, objectLocation);
        if(objectEntity != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(objectEntity);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/assignNfc")
    public ResponseEntity<ObjectEntity> assignNfc(@RequestBody ObjectEntity objectEntity, @RequestParam NfcEntity nfcEntity){
        ObjectEntity assignedObject = adminService.assignNfc(objectEntity, nfcEntity);
        if(assignedObject != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(assignedObject);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/removeObject")
    public ResponseEntity<String> removeObject(@RequestBody ObjectEntity objectEntity){
        adminService.removeObject(objectEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Remove Successful");
    }
}
