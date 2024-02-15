package GDSCKNU.VitaBelly.service;

import GDSCKNU.VitaBelly.dto.Location;
import GDSCKNU.VitaBelly.dto.PlaceResult;
import GDSCKNU.VitaBelly.dto.PlacesSearchResponse;
import GDSCKNU.VitaBelly.model.Clinic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

@Service
public class ClinicService {

    @Value("${google.api.key}")
    private String googleApiKey;

    private final RestTemplate restTemplate;

    public ClinicService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Clinic> findClinicsInCity(String cityName) {
        List<Clinic> clinics = new ArrayList<>();
        String query = cityName + " 산부인과";
        String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query="
            + query + "&key=" + googleApiKey;
        ResponseEntity<PlacesSearchResponse> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {
            });
        List<PlaceResult> results = response.getBody().getResults();

        for (PlaceResult result : results) {
            Clinic clinic = new Clinic();
            clinic.setName(result.getName());
            clinic.setAddress(result.getFormatted_address());
            Location location = result.getGeometry().getLocation();
            clinic.setLatitude(location.getLat());
            clinic.setLongitude(location.getLng());
            clinics.add(clinic);
        }

        return clinics;
    }
}
