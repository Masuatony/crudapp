package com.example.firstcrud.CountyRecon;

import com.example.firstcrud.CountyRecon.CountyRecon.CountryRecon;
import com.example.firstcrud.CountyRecon.Model.County;
import com.example.firstcrud.CountyRecon.Repos.CountyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v3/county")
public class HandlerCounty {
    @Autowired
    private CountyRepo countyRepo;
    @Autowired
    private CountryRecon recon;

    @PostMapping("/initiate")
    public ResponseEntity<?> initiateRecon() throws IOException{
        try {
            boolean county = recon.reconcile();
            return ResponseEntity.ok( new resp("Files read", HttpStatus.PROCESSING.value()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok( new resp("failed to read",HttpStatus.FORBIDDEN.value()));
        }
    }
    record resp(String message, Integer statusCode){}
}
