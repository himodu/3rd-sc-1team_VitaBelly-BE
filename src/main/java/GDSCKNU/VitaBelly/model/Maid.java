package GDSCKNU.VitaBelly.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Maid {
    private int id; // Firebase에서 생성된 고유 ID
    private String profileImageUrl;
    private String name;
    private String introduction; // 자기소개
    private Location location; // 위치 정보
    private double ratings;
    private List<String> services; // 제공하는 서비스 종류

    @Builder
    public Maid(int id, String profileImageUrl, String name, String introduction, Location location, double ratings, List<String> services) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.introduction = introduction;
        this.location = location;
        this.ratings = ratings;
        this.services = services;
    }

    @Data
    public static class Location {
        private String city;
        private String district; // 구/군
        private String detail; // 상세 주소

        public Location() {

        }
        public Location(String city, String district, String detail) {
            this.city = city;
            this.district = district;
            this.detail = detail;
        }
    }
}
