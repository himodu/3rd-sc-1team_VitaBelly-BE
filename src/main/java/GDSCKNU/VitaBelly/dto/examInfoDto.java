package GDSCKNU.VitaBelly.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class examInfoDto {

    private int id;
    private int startWeek;
    private int endWeek;
    private String examType;
    private String explane;

}
