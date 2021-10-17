package com.pipertzis.FarmHelper_BackEnd.Repositories;

import com.pipertzis.FarmHelper_BackEnd.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
