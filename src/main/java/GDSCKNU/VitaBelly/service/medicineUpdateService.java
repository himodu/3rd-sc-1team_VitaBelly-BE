package GDSCKNU.VitaBelly.service;

import GDSCKNU.VitaBelly.entity.medicineInfo;
import GDSCKNU.VitaBelly.repository.medicineInfoRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class medicineUpdateService {

    private final medicineInfoRepository medicineInfoRepository;

    public void updateMedicineInfo(){
        medicineInfoRepository.deleteAll();
        for(int i =0; i<200; i+=25){
            this.getMedicineInfo(i);
        }
    }
    public void getMedicineInfo(int page) {

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory("https://api.odcloud.kr");
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        String baseURL = "https://api.odcloud.kr";
        String basePath = "/api/15067466/v1/uddi:c741e770-ac42-419f-9b4f-d6e0cb3d63ae?";
        String apiKey = "rk4TRNlsd5PWdUjdkgQg19%2BU8zwJsIMFSvZGHdRjfPx9rR813K9Y2FHRTB8H5%2BjKDeyDUWkMc9AJA2LATV%2Be3Q%3D%3D";
        String pageStr = String.valueOf(page);
        String perPageStr = String.valueOf(100);
        // WebClient URL, firebase로 API요청을 보내기 위해서.
        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(baseURL)
                .build();

        System.out.println("https://api.odcloud.kr/api/15067466/v1/uddi:c741e770-ac42-419f-9b4f-d6e0cb3d63ae?page=" + pageStr +"&perPage="+perPageStr+"&serviceKey="+apiKey);

        // Body elements
        try {
            String mediInfoStr = webClient.get()
                    .uri("https://api.odcloud.kr/api/15067466/v1/uddi:c741e770-ac42-419f-9b4f-d6e0cb3d63ae?page=" + pageStr +"&perPage="+perPageStr+"&serviceKey="+apiKey)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            JSONObject mediInfos = new JSONObject(mediInfoStr);
            System.out.println(mediInfos);

            JSONArray data = mediInfos.getJSONArray("data");


            for(int i=0; i<10; i++){
                JSONObject medi = data.getJSONObject(i);

                medicineInfo medicine = medicineInfo.builder()
                        .grade(medi.getInt("금기등급"))
                        .medicineName(medi.getString("제품명"))
                        .medicineCom(medi.getString("업체명"))
                        .explane(medi.getString("상세정보"))
                        .build();
                medicineInfoRepository.save(medicine);
            }
        }catch(WebClientResponseException e){
            e.printStackTrace();
            System.out.println(e.getResponseBodyAsString());
        }
    }
}
