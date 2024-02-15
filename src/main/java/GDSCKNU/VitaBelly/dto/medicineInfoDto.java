package GDSCKNU.VitaBelly.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class medicineInfoDto {
    private int id;
    private int grade;
    private String imageLink;
    private String medicineName;
    private String medicineCom;
}
