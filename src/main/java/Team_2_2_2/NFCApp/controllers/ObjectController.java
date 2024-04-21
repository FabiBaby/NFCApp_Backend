package Team_2_2_2.NFCApp.controllers;

import Team_2_2_2.NFCApp.entities.ObjectEntity;
import Team_2_2_2.NFCApp.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController //Means that this class is a controller
@RequestMapping("/objects")
public class ObjectController {
    private final ObjectService objectService;

    @Autowired
    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @GetMapping("/getObjectInfoByNfcId") //Endpoint to get an object by ID
    public ResponseEntity<ObjectEntity> getObjectInfoByNfcId(@RequestParam String NfcId) {
        ObjectEntity objectEntity = objectService.getObjectInfoByNfcId(NfcId);
        if(objectEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(objectEntity);
    }
}
