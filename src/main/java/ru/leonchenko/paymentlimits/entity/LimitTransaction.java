package ru.leonchenko.paymentlimits.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "limit_transaction")
public class LimitTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "user_id")
    private Long userId;

    public LimitTransaction(BigDecimal amount, Long userId) {
        this.amount = amount;
        this.userId = userId;
    }
}