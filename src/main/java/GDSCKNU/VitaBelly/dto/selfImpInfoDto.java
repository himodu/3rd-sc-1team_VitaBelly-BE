package GDSCKNU.VitaBelly.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class selfImpInfoDto {

    private int id;
    private String selfImpType;
    private List<String> detailList;
}
