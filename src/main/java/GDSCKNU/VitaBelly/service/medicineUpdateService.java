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
import java.util.List;

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


        //약물 이미지는 임의의 5개를 랜덤으로 부여
        List<String> imgLinkList = new ArrayList<>();

        imgLinkList.add("https://firebasestorage.googleapis.com/v0/b/image-ef7d2.appspot.com/o/12594a39a8ab48a508b290c51f18b4c1.png?alt=media");
        imgLinkList.add("https://firebasestorage.googleapis.com/v0/b/image-ef7d2.appspot.com/o/1f637bb9b34310ddb251f326cc869842.png?alt=media");
        imgLinkList.add("https://firebasestorage.googleapis.com/v0/b/image-ef7d2.appspot.com/o/7adccd4ab256027f0861f040339eef51.png?alt=media");
        imgLinkList.add("https://firebasestorage.googleapis.com/v0/b/image-ef7d2.appspot.com/o/e4ff664030332958916cbc9736ed331e.png?alt=media");
        imgLinkList.add("https://firebasestorage.googleapis.com/v0/b/image-ef7d2.appspot.com/o/fce9fdaa7e0967f6ab040321e6faa34b.png?alt=media");

      int randVal;

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory("https://api.odcloud.kr");
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        String baseURL = "https://api.odcloud.kr";
        String apiKey = "rk4TRNlsd5PWdUjdkgQg19%2BU8zwJsIMFSvZGHdRjfPx9rR813K9Y2FHRTB8H5%2BjKDeyDUWkMc9AJA2LATV%2Be3Q%3D%3D";
        String pageStr = String.valueOf(page);
        String perPageStr = String.valueOf(100);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(baseURL)
                .build();
        try {
            String mediInfoStr = webClient.get()
                    .uri("https://api.odcloud.kr/api/15067466/v1/uddi:c741e770-ac42-419f-9b4f-d6e0cb3d63ae?page=" + pageStr +"&perPage="+perPageStr+"&serviceKey="+apiKey)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            JSONObject mediInfos = new JSONObject(mediInfoStr);

            JSONArray data = mediInfos.getJSONArray("data");

            for(int i=0; i<10; i++){
                JSONObject medi = data.getJSONObject(i);

                randVal = (int)(Math.random()*5);

                medicineInfo medicine = medicineInfo.builder()
                        .grade(medi.getInt("금기등급"))
                        .medicineName(medi.getString("제품명"))
                        .medicineCom(medi.getString("업체명"))
                        .explane(medi.getString("상세정보"))
                        .imageLink(imgLinkList.get(randVal))
                        .build();
                medicineInfoRepository.save(medicine);
            }
        }catch(WebClientResponseException e){
            e.printStackTrace();
            System.out.println(e.getResponseBodyAsString());
        }
    }
}
