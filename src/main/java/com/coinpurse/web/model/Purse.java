package com.coinpurse.web.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "purses")
public class Purse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @CreationTimestamp
    private LocalDateTime creation;
    private Float amount; //TODO: Remove, replace with 1:N relationship
    private String currency;

    @OneToMany(mappedBy = "purse", cascade = CascadeType.REMOVE)
    private Set<Event> events = new HashSet<>();
}
