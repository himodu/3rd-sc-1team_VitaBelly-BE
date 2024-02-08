package GDSCKNU.VitaBelly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import GDSCKNU.VitaBelly.entity.selfImpInfo;

@Repository
public interface selfImpInfoRepository extends JpaRepository<selfImpInfo, Long> {
}
