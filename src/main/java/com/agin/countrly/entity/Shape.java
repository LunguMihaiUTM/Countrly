package com.agin.countrly.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Shape {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shape_id_seq")
    @SequenceGenerator(name = "shape_id_seq", sequenceName = "shape_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "country_name", nullable = false)
    private String name;

    @Column(name = "image", nullable = false)
    private String image;
}
