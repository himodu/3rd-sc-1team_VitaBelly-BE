package GDSCKNU.VitaBelly.repository;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import GDSCKNU.VitaBelly.entity.medicineInfo;

@Repository
public interface medicineInfoRepository extends JpaRepository<medicineInfo, Long> {
}
