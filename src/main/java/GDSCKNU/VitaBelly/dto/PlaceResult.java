package GDSCKNU.VitaBelly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceResult {

    private String name;
    private String formatted_address;
    private Geometry geometry;
}
