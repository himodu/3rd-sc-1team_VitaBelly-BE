package GDSCKNU.VitaBelly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import GDSCKNU.VitaBelly.entity.*;
import org.springframework.stereotype.Repository;

@Repository
public interface maidInfoRepository extends JpaRepository<maidInfo, Long> {
}
