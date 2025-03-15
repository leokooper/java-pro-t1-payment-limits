package ru.leonchenko.paymentlimits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leonchenko.paymentlimits.entity.LimitTransaction;

public interface LimitTransactionRepository extends JpaRepository<LimitTransaction, Long> {

}
