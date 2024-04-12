package Team_2_2_2.NFCApp.controllers;

import Team_2_2_2.NFCApp.entities.NfcEntity;
import Team_2_2_2.NFCApp.services.NfcService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nfc")
public class NfcController {
    private NfcService nfcService;

    @Autowired
    public NfcController(NfcService nfcService) {
        this.nfcService = nfcService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addNfc(@RequestParam String nfcId) {
        nfcService.addNfc(nfcId);

        return ResponseEntity.ok("NFC added");
    }
}
