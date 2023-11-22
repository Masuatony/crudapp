package com.example.firstcrud.Workbook.Handler;

import com.example.firstcrud.DTO.EntityResponse;
import com.example.firstcrud.Workbook.Entity.Ifmis;
import com.example.firstcrud.Workbook.ExcelReader;
import com.example.firstcrud.Workbook.IfmisRepo.IfmisRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class HandlerIfm {
//    @Value("/home/tm/Downloads/new/")
    private String filePath = "/home/tm/REPOS/ME/firstCrud/src/main/resources/County/3711.xlsx";
    @Autowired
    private final ExcelReader excelReader;
    @Autowired
    private final IfmisRepo ifmisRepo;

    public HandlerIfm(ExcelReader excelReader, IfmisRepo ifmisRepo) {
        this.excelReader = excelReader;
        this.ifmisRepo = ifmisRepo;
    }
//    @PostMapping("/read")
//    public ResponseEntity<?> read() throws IOException {
//        Ifmis list = (Ifmis) excelReader.read(filePath);
//        ifmisRepo.save(list);
//
//        return ResponseEntity.ok().body(list);
//    }
@PostMapping("/read/new")
public ResponseEntity<?> read() throws IOException {
    // Read data from Excel file
    log.info("-----Handling read request-----");
    Ifmis list = (Ifmis) excelReader.read(filePath);
    List<Ifmis> ifmisList = excelReader.read(filePath);

    // Check for null values
    if (list == null) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading data from Excel");
    }

    try {
        // Save data to the repository
//        Ifmis savedData = ifmisRepo.save(list);
        List<Ifmis> ifmisList1 = ifmisRepo.saveAll(ifmisList);
        return ResponseEntity.ok().body(ifmisList1);
    } catch (Exception e) {
        // Handle exceptions (e.g., database errors)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data to the repository");
    }
}
    @GetMapping("/read")
    public ResponseEntity<?> readS() throws IOException {
        List<Ifmis> exist = ifmisRepo.findAll();
        ifmisRepo.deleteAll();
//        if(!exist.isEmpty()){
//            ifmisRepo.deleteAll();
//        }
        // Read data from Excel file
        log.info("-----Handling read request-----");
        List<Ifmis> ifmisList = excelReader.read(filePath);

        ifmisRepo.saveAll(ifmisList);
        // Check for null or empty list
        if (ifmisList == null || ifmisList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading data from Excel");
        }

        try {
            // Save data to the repository
//            Ifmis savedData = ifmisRepo.save(ifmisList.get(0)); // Assuming you want to save the first Ifmis object
            return ResponseEntity.status(HttpStatus.OK).body("Read cmplete");
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data to the repository");
        }
    }

    @GetMapping("/get/ifmis")
    public ResponseEntity<?> getAll(){
        try{
            EntityResponse response = new EntityResponse<>();
            List<Ifmis> ifmisList = ifmisRepo.findAll();
            response.setMessage("found all");
            response.setEntity(ifmisList);
            response.setStatusCode(HttpStatus.FOUND.value());
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }





}
