package GDSCKNU.VitaBelly.model;

import java.util.List;
import lombok.Data;

@Data
public class Maid {
    private String id; // Firebase에서 생성된 고유 ID
    private String profileImageUrl;
    private String name;
    private String introduction; // 자기소개
    private Location location; // 위치 정보
    private double ratings;
    private List<String> services; // 제공하는 서비스 종류

    public static class Location {
        private String city;
        private String district; // 구/군
        private String detail; // 상세 주소

        public Location() {

        }
    }
}
