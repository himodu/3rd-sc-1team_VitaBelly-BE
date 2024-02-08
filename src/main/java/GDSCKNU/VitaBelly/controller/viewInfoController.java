package GDSCKNU.VitaBelly.controller;

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

    @GetMapping("examInfo")
    public List<examInfoDto> getAllexamInfo(){
        return viewInfoService.getAlllexamInfo();
    }

    @GetMapping("examInfo/{examId}")
    public examInfoDto getExamInfo(
            @PathVariable("examID") int id
    ){
        return viewInfoService.getExamInfo(id);
    }

    @GetMapping("medicineInfo")
    public List<medicineInfoDto> getAllmedicineInfo(
            @RequestParam("sortBy") String sortBy
    ){
        return viewInfoService.getAllmedicineInfo(sortBy);
    }

    @GetMapping("medicineInfo/{medicineId}")
    public medicineInfoDto getMedicineInfo(
            @PathVariable("medicineId") int id
    ){
        return viewInfoService.getMedicineInfo(id);
    }

    @GetMapping("selfImpInfo")
    public List<selfImpInfoDto> getAllselfImpInfo(){
        return viewInfoService.getAllselfImpInfo();
    }


}
