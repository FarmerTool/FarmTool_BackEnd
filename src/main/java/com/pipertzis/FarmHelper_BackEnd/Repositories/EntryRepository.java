package com.pipertzis.FarmHelper_BackEnd.Repositories;

import com.pipertzis.FarmHelper_BackEnd.Models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntryRepository extends JpaRepository<Entry, UUID> {


}
