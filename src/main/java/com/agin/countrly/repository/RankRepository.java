package com.agin.countrly.repository;

import com.agin.countrly.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    Optional<Rank> findByUserId(Long userid);
    @Modifying
    @Query("UPDATE ranks r SET r.rating = r.rating + :increment WHERE r.userId = :userId")
    int incrementRatingByUserId(@Param("userId") Long userId, @Param("increment") Double increment);
}
