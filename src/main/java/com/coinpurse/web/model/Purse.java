package com.coinpurse.web.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Purses")
public class Purse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @CreationTimestamp
    private LocalDateTime creation;
    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;

//    @ManyToOne
//    @JoinColumn(name = "created_by", nullable = false)
//    private UserEntity createdBy;
    @OneToMany(mappedBy = "purse", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Event> events = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Purse)) return false;
        if(((Purse) o).getId() != null && ((Purse) o).getId().equals(this.getId())) return true;
        return this == o;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
