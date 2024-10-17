package com.agin.countrly.entity;

import com.agin.countrly.enums.ComplexityEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Complexity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complexity_id_seq")
    @SequenceGenerator(name = "complexity_id_seq", sequenceName = "complexity_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "complexity", nullable = false)
    private ComplexityEnum complexity;

    @Column(name = "country_id")
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_COUNTRY_COMPLEXITY"))
    private Long countryId;
}
