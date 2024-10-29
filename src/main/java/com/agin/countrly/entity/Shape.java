package com.agin.countrly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "shapes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shape {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shape_id_seq")
    @SequenceGenerator(name = "shape_id_seq", sequenceName = "shape_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "country_id")
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_COUNTRY_SHAPE"))
    private Long countryId;
}
