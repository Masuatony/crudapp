package com.example.firstcrud.GovernorMinistry.Service;

import com.example.firstcrud.DTO.EntityResponse;
import com.example.firstcrud.GovernorMinistry.FileReaders.GovernorReaders;
import com.example.firstcrud.GovernorMinistry.Models.Governor;
import com.example.firstcrud.GovernorMinistry.Repository.GovernorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GovernorRecon {
    @Autowired
    private GovernorReaders governorReaders;

    @Autowired
    private GovernorRepo governorRepo;
    String recuPath = "/home/tm/Documents/Recurrent.xlsx";
    String devPath = "/home/tm/Documents/devepment.xlsx";

    public EntityResponse reconcile() throws IOException{
        EntityResponse response = new EntityResponse<>();
         try {
        File recuFile = new File(recuPath);
        File devFile = new File(devPath);
        if(!devFile.exists()){
            response.setMessage("File not found! ");
            response.setEntity(HttpStatus.NOT_ACCEPTABLE.value());
        }
        if(!recuFile.exists()){
            response.setMessage("File not found! ");
            response.setEntity(HttpStatus.NOT_ACCEPTABLE.value());
        }
        Governor governor = new Governor();

        List<Governor> recurrent = (List<Governor>) governorReaders.readRecurrent(recuPath);

        List<Governor> development = governorReaders.readDevelopment(devPath);
//        response = governorReaders.readRecurrent(recuPath);
//        if(response.getStatusCode() != HttpStatus.OK.value()){
//            return response;
//        }
//        recurrent = (List<Governor>) response.getEntity();

//        response = (EntityResponse) governorReaders.readDevelopment(devPath);
        recurrent.add(governor);
        development.add(governor);
        governorRepo.saveAll(recurrent);
        governorRepo.saveAll(development);
        return response;

    }catch (Exception e){
        return null;
    }
    }



}
