package GDSCKNU.VitaBelly.auth;

import GDSCKNU.VitaBelly.auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserEmail(String userEmail);
}
