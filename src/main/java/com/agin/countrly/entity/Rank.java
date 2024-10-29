package com.agin.countrly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ranks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rank {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rank_id_seq")
    @SequenceGenerator(name = "rank_id_seq", sequenceName = "rank_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "user_id")
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER_RANK"))
    private Long userId;
}
