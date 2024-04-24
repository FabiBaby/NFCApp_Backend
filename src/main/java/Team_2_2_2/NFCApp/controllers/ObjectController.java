package Team_2_2_2.NFCApp.controllers;

import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  // Marks this class as a controller with @ResponseBody, meaning that methods return values are bound to the web response body
@RequestMapping("/objects")  // Maps all HTTP requests that start with "/objects" to methods in this controller
public class ObjectController {
    private final ObjectService objectService;  // Declares a dependency on ObjectService

    @Autowired  // Marks the constructor for dependency injection of ObjectService
    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;  // Injects the object service into the controller
    }

    @GetMapping("/getObjectInfoByNfcId")  // Maps HTTP GET requests to "/getObjectInfoByNfcId", handling them with this method
    public ResponseEntity<ObjectEntity> getObjectInfoByNfcId(@RequestParam String NfcId) {  // Accepts an NFC ID as a request parameter
        ObjectEntity objectEntity = objectService.getObjectInfoByNfcId(NfcId);  // Retrieves an object entity based on the NFC ID
        if (objectEntity == null) {  // Checks if no entity was found for the given NFC ID
            return ResponseEntity.notFound().build();  // Returns a 404 Not Found response if no object is found
        }
        return ResponseEntity.ok(objectEntity);  // Returns the found object entity with an OK status
    }
}
