package GDSCKNU.VitaBelly.controller;

import GDSCKNU.VitaBelly.model.Maid;
import GDSCKNU.VitaBelly.service.MaidService;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maids")
public class MaidController {

    private final MaidService maidService;

    @Autowired
    public MaidController(MaidService maidService) {
        this.maidService = maidService;
    }

    @PostMapping
    @Operation(summary = "가사도우미 게시글 작성", description = "가사도우미 게시글을 작성합니다.")
    public ResponseEntity<String> addMaid(@RequestBody Maid maid) {
        maidService.addMaid(maid);
        return ResponseEntity.ok("sucess");
    }

    @GetMapping
    @Operation(summary = "가사도우미 게시글 불러오기", description = "가사도우미 정보를 모두 불러옵니다.")
    public ResponseEntity<List<Maid>> getAllMaids() {
        return ResponseEntity.ok(maidService.getAllMaids());
    }

    @GetMapping("/{maidinfoId}")
    @Operation(summary = "가사도우미 게시글 불러오기", description = "가사도우미 정보를 모두 불러옵니다.")
    public ResponseEntity<Maid> getMaid(
            @PathVariable("maidinfoId")int id
    ) {
        return ResponseEntity.ok(maidService.getMaid(id));
    }

//    @PutMapping("/{maidId}")
//    @Operation(summary = "가사도우미 게시글 수정", description = "가사도우미 게시글을 수정합니다.")
//    public ResponseEntity<Void> updateMaid(
//            @PathVariable String maidId,
//            @RequestBody Maid maid
//    ) {
//
//    }
//
//    @DeleteMapping("/{maidId}")
//    @Operation(summary = "가사도우미 게시글 삭제", description = "가사도우미 게시글을 삭제합니다.")
//    public ResponseEntity<Void> deleteMaid(
//            @PathVariable String maidId
//    ) {
//
//    }
}
