package com.pipertzis.FarmHelper_BackEnd.Repositories;

import com.pipertzis.FarmHelper_BackEnd.Models.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, UUID> {
    List<Fruit> findByUser_userId(UUID userId);

    boolean existsFruitByFruitNameAndUser_userId(String fruitName, UUID userId);

    boolean existsFruitByFruitName(String fruitName);
}
