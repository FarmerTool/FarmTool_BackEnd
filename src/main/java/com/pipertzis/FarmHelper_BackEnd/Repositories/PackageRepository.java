package com.pipertzis.FarmHelper_BackEnd.Repositories;

import com.pipertzis.FarmHelper_BackEnd.Models.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PackageRepository extends JpaRepository<Package, UUID> {
    Boolean existsByPackageNameAndFruit_FruitId(String packageName, UUID fruitId);

    List<Package> findByFruit_FruitId(UUID fruitId);

}
