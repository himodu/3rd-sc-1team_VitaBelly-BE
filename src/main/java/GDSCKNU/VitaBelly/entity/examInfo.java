package GDSCKNU.VitaBelly.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class examInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start")
    private int startWeek;
    @Column(name = "end")
    private int endWeek;

    @Column(name = "examType")
    private String examType;

    @Column(name = "explane")
    private String explane;


}
