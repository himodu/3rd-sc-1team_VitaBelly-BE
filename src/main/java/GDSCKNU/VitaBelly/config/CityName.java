package GDSCKNU.VitaBelly.config;

import lombok.Getter;

public enum CityName {
    SEOUL("seoul", "서울"),
    BUSAN("busan", "부산"),
    DAEGU("daegu", "대구"),
    INCHEON("incheon", "인천");

    private final String englishName;
    @Getter
    private final String koreanName;

    CityName(String englishName, String koreanName) {
        this.englishName = englishName;
        this.koreanName = koreanName;
    }

    public static String parseCityName(String englishName) {
        for (CityName cityName : CityName.values()) {
            if (cityName.englishName.equalsIgnoreCase(englishName)) {
                return cityName.getKoreanName();
            }
        }
        throw new IllegalArgumentException("Unknown city name: " + englishName);
    }
}

