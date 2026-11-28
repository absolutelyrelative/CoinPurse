package com.coinpurse.web.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String currency;
    private String currencyDescription;
    private BigDecimal conversionRatioToEur;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdon;
    @UpdateTimestamp
    private LocalDateTime updatedon;

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Currency)) return false;
        if(((Currency) o).getId() != null && ((Currency) o).getId().equals(this.getId())) return true;
        return this == o;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
