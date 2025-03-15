package ru.leonchenko.paymentlimits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leonchenko.paymentlimits.entity.UserLimit;
import java.util.Optional;

public interface UserLimitRepository extends JpaRepository<UserLimit, Long> {

    Optional<UserLimit> findByUserId(Long userId);
}
