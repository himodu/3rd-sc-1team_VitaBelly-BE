package GDSCKNU.VitaBelly.controller;

import GDSCKNU.VitaBelly.config.CityName;
import GDSCKNU.VitaBelly.model.Clinic;
import GDSCKNU.VitaBelly.service.ClinicService;
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
    public ResponseEntity<List<Clinic>> getClinicsByCity(@PathVariable String cityName) {
        String koreanCityName = CityName.parseCityName(cityName.toLowerCase());
        List<Clinic> clinics = clinicService.findClinicsInCity(koreanCityName);
        return ResponseEntity.ok(clinics);
    }
}

