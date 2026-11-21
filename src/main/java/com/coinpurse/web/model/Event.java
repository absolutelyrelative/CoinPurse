package com.coinpurse.web.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private LocalDateTime date;
    @CreationTimestamp
    private LocalDateTime createdon;
    @UpdateTimestamp
    private LocalDateTime updatedon;
    private String type; //TODO: Change to enum?
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal delta;
    @Column(precision = 19, scale = 2)
    private BigDecimal finalValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="purse_id", nullable=false)
    private Purse purse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="currency_id", nullable=false)
    private Currency currency;

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Event)) return false;
        if(((Event) o).getId() != null && ((Event) o).getId().equals(this.getId())) return true;
        return this == o;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
