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

    @PostMapping //Endpoint to assign nfc
    public ResponseEntity<NfcEntity> assignNfc(@RequestBody NfcObjectDTO nfcObjectDTO) {
        NfcEntity newNfc = nfcService.assignNfc(
                nfcObjectDTO.getNfcEntity(),
                nfcObjectDTO.getObjectId()
        );

        return ResponseEntity.ok(newNfc);
    }

    @PatchMapping("/{id}/unassign") //Use PATCH to indicate a partial update
    public ResponseEntity<NfcEntity> unassignNfc(@PathVariable Long nfcId) {
        try{
            NfcEntity nfcEntity = nfcService.unassignNfc(nfcId);
            return ResponseEntity.ok(nfcEntity);
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    static class NfcObjectDTO {
        private NfcEntity nfcEntity;
        private Long objectId;

        public NfcEntity getNfcEntity() {
            return nfcEntity;
        }

        public void setNfcEntity(NfcEntity nfcEntity) {
            this.nfcEntity = nfcEntity;
        }

        public Long getObjectId() {
            return objectId;
        }

        public void setObjectId(Long objectId) {
            this.objectId = objectId;
        }
    }
}
