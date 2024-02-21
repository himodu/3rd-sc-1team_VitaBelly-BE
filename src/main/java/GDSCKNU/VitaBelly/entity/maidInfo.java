package GDSCKNU.VitaBelly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class maidInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profileImageUrl")
    private String profileImageUrl;
    @Column(name = "name")
    private String name;
    @Column(name = "introduction")
    private String introduction;
    @Column(name = "city")
    private String city;
    @Column(name = "district")
    private String district;
    @Column(name = "detail")
    private String detail;
    @Column(name = "ratings")
    private int ratings;
    @ElementCollection
    @CollectionTable(name="services", joinColumns =  @JoinColumn(name="maidInfoId", referencedColumnName = "id"))
    private List<String> services= new ArrayList<>();
}
