package GDSCKNU.VitaBelly.controller;

import GDSCKNU.VitaBelly.model.Maid;
import GDSCKNU.VitaBelly.service.MaidService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maids")
public class MaidController {

    private final MaidService maidService;

    @Autowired
    public MaidController(MaidService maidService) {
        this.maidService = maidService;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<String>> addMaid(@RequestBody Maid maid) {
        return maidService.addMaid(maid)
            .thenApply(maidId -> ResponseEntity.ok().body("Maid added with ID: " + maidId))
            .exceptionally(error -> ResponseEntity.badRequest().body("Error: " + error.getMessage()));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Maid>>> getAllMaids() {
        return maidService.getAllMaids()
            .thenApply(maids -> ResponseEntity.ok().body(maids))
            .exceptionally(error -> ResponseEntity.badRequest().body(null));
    }

    @PutMapping("/{maidId}")
    public CompletableFuture<ResponseEntity<Void>> updateMaid(@PathVariable String maidId,
        @RequestBody Maid maid) {
        return maidService.updateMaid(maidId, maid)
            .thenApply(aVoid -> ResponseEntity.ok().<Void>build())
            .exceptionally(error -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{maidId}")
    public CompletableFuture<ResponseEntity<Void>> deleteMaid(@PathVariable String maidId) {
        return maidService.deleteMaid(maidId)
            .thenApply(aVoid -> ResponseEntity.ok().<Void>build())
            .exceptionally(error -> ResponseEntity.badRequest().build());
    }
}
