package com.pipertzis.FarmHelper_BackEnd.Repositories;

import com.pipertzis.FarmHelper_BackEnd.Models.Variety;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VarietyRepository extends JpaRepository<Variety, UUID> {
    List<Variety> findByFruit_fruitId(UUID fruitId);

    List<Variety> findByUser_userId(UUID userId);
}
