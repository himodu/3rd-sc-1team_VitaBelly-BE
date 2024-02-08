package GDSCKNU.VitaBelly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import GDSCKNU.VitaBelly.entity.examInfo;

@Repository
public interface examInfoRepository extends JpaRepository<examInfo, Long> {
}
