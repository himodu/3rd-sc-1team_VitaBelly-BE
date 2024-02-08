package GDSCKNU.VitaBelly.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
public class selfImpInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "selfImpType")
    private String selfImpType;

    @ElementCollection
    @CollectionTable(name="details", joinColumns =  @JoinColumn(name="selfImp_id", referencedColumnName = "id"))
    private List<String> detailList= new ArrayList<>();
}
