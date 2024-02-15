package GDSCKNU.VitaBelly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class medicineInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade")
    private int grade;

    @Column(name = "imageLink")
    private String imageLink;

    @Column(name = "medicineName")
    private String medicineName;

    @Column(name = "medicineCom")
    private String medicineCom;

    @Column(name = "medicineExplane")
    private String explane;
}
