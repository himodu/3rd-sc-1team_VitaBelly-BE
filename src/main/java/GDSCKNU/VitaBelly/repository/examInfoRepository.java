package GDSCKNU.VitaBelly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import GDSCKNU.VitaBelly.entity.examInfo;

import java.util.Optional;

@Repository
public interface examInfoRepository extends JpaRepository<examInfo, Long> {
    Optional<examInfo> findById(Long id);
}
