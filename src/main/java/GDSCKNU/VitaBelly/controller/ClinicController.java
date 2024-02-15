package GDSCKNU.VitaBelly.controller;

import GDSCKNU.VitaBelly.config.CityName;
import GDSCKNU.VitaBelly.model.Clinic;
import GDSCKNU.VitaBelly.service.ClinicService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clinics") // 기본 URL 경로 설정
public class ClinicController {

    private final ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/{cityName}")
    @Operation(summary = "도시별 산부인과,산후조리원 불러오기", description = "cityName 으로 도시를 입력하면 그에 맞게 정보를 불러옵니다.")
    public ResponseEntity<List<Clinic>> getClinicsByCity(@PathVariable String cityName) {
        String koreanCityName = CityName.parseCityName(cityName.toLowerCase());
        List<Clinic> clinics = clinicService.findClinicsInCity(koreanCityName);
        return ResponseEntity.ok(clinics);
    }
}

