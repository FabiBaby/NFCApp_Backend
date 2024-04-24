package Team_2_2_2.NFCApp.controllers;

import Team_2_2_2.NFCApp.services.NfcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  // Marks the class as a REST controller, handling web requests
@RequestMapping("/nfc")  // Sets the base path for URLs in this controller to handle requests beginning with /nfc
public class NfcController {
    private NfcService nfcService;  // Service layer dependency for NFC operations

    @Autowired  // Automatically injects the NfcService dependency
    public NfcController(NfcService nfcService) {
        this.nfcService = nfcService;  // Constructor injection of the NfcService
    }

    @PostMapping("/addNfc")  // Maps HTTP POST requests to /addNfc. Used for adding a new NFC tag
    public ResponseEntity<String> addNfc(@RequestParam String nfcId) {  // Accepts the NFC ID as a request parameter
        nfcService.addNfc(nfcId);  // Calls the service layer to add the NFC tag
        return ResponseEntity.ok("NFC added");  // Returns an OK status with a message confirming addition
    }
}
