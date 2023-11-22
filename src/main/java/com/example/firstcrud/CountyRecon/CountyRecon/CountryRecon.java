package com.example.firstcrud.CountyRecon.CountyRecon;

import com.example.firstcrud.CountyRecon.Model.County;
import com.example.firstcrud.CountyRecon.Readers.ReadersCounty;
import com.example.firstcrud.CountyRecon.Repos.CountyRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryRecon {
    @Autowired
    private ReadersCounty readersCounty;
    @Autowired
    private CountyRepo countyRepo;

    public boolean reconcile() throws IOException {
//        countyRepo.deleteAll();
//        List<County> countyDep = readersCounty.readDep();
        List<County> countyRec = readersCounty.readRec();
        List<County> countyDev = readersCounty.readDev();
        List<County> countyDep = readersCounty.readDep();

        log.info("-----dev,rec,dep", countyRec.size(),countyDep.size(),countyDev.size());

//        County countyList = ?
        countyRepo.saveAll(countyRec);
        countyRepo.saveAll(countyDep);
        countyRepo.saveAll(countyDev);
        return true;
    }
}
