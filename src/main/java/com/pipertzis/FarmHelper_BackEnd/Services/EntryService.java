package com.pipertzis.FarmHelper_BackEnd.Services;
/*
    Created by Piper on 10/22/2021
*/

import com.pipertzis.FarmHelper_BackEnd.Repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;


}
