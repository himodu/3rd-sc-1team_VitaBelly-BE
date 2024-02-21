package GDSCKNU.VitaBelly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import GDSCKNU.VitaBelly.entity.*;

@Repository
public interface maidInfoRepository extends JpaRepository<maidInfo, Long> {
}
