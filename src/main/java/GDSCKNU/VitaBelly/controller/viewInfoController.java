package GDSCKNU.VitaBelly.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import GDSCKNU.VitaBelly.entity.*;
import GDSCKNU.VitaBelly.service.*;
import GDSCKNU.VitaBelly.dto.*;

import java.util.List;

@RestController
@RequestMapping("viewInfo")
@RequiredArgsConstructor
public class viewInfoController {

    private final viewInfoService viewInfoService;
    private final medicineUpdateService medicineUpdateService;

    @GetMapping("examInfo")
    @Operation(summary = "주차별 검사 모두 불러오기", description = "주차별 검사를 모두 불러옵니다.")
    public List<examInfoDto> getAllexamInfo(){
        return viewInfoService.getAlllexamInfo();
    }

    @GetMapping("examInfo/{examId}")
    @Operation(summary = "주차별 검사 하나만 불러오기", description = "주차별 검사를 id를 기준으로 하나만 불러옵니다.")
    public examInfoDto getExamInfo(
            @PathVariable("examId") int id
    ){
        return viewInfoService.getExamInfo(id);
    }

    @GetMapping("medicineInfo")
    @Operation(summary = "의약품 정보 모두 불러오기", description = "queryParameter 값을 기준으로 의약품정보를 정렬하여 불러옵니다. (grade=금기등급순, name=제품명순, company=업체명순)")
    public List<medicineInfoDto> getAllmedicineInfo(
            @RequestParam("sortBy") String sortBy
    ){
        return viewInfoService.getAllmedicineInfo(sortBy);
    }

    @GetMapping("medicineInfo/{medicineId}")
    @Operation(summary = "의약품 정보 하나만 불러오기", description = "의약품 정보를 모두 불러옵니다.")
    public medicineInfoDto getMedicineInfo(
            @PathVariable("medicineId") int id
    ){
        return viewInfoService.getMedicineInfo(id);
    }

    @GetMapping("selfImpInfo")
    @Operation(summary = "자기관리 정보 모두 불러오기", description = "자기관리 정보를 모두 불러옵니다.")
    public List<selfImpInfoDto> getAllselfImpInfo(){
        return viewInfoService.getAllselfImpInfo();
    }

    @GetMapping("updateMedicine")
    @Operation(summary = "의약품 정보 업데이트", description = "공공데이터 포털 api 를 사용하여 의약품 정보를 업데이트합니다.")
    public void usingApi(
    ){
        medicineUpdateService.updateMedicineInfo();
    }


}
