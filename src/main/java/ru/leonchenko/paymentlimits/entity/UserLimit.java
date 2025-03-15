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
@Table(name = "user_limit")
public class UserLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "`limit`")
    private BigDecimal limit;

    @Column(name = "user_id")
    private Long userId;

    public UserLimit(Long userId) {
        this.userId = userId;
        this.limit = new BigDecimal("10000.00");
    }
}