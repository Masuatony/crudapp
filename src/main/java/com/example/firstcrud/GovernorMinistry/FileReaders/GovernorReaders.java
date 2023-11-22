package com.example.firstcrud.GovernorMinistry.FileReaders;

import com.example.firstcrud.DTO.EntityResponse;
import com.example.firstcrud.GovernorMinistry.Models.Governor;
import com.example.firstcrud.Workbook.Entity.Ifmis;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class GovernorReaders {
    public EntityResponse readRecurrent(String path) {
        EntityResponse response = new EntityResponse<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            boolean skipFirstRow = true;
            Integer i = 0;
            List<Governor> recurrent = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // TODO: 6/29/2023 skip the first six
                i++;
                if (i < 6) {
                    skipFirstRow = false;
                    continue;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public Date parseDate(String dateString) {
        if(dateString.trim().isEmpty()){
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
        try {
            Date parsedDate = dateFormat.parse(dateString);
            return parsedDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Read recurrent
    public List<Governor> read(String filePath) throws IOException {
        log.info("-----Initializing Reading----",filePath);
        List<Governor> recurrent = new ArrayList<>();

        File newFile = new File(filePath);
        if (!newFile.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        log.info("-----reading----");
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Integer rowNum = 0;
        for (Row record : sheet) {
            if (record.getRowNum() == 0) {
                continue;
            }
//            log.info("-----processing at row" + rowNum++);
            Governor entity = new Governor();

            //format the date to required date pattern
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
            SimpleDateFormat required = new SimpleDateFormat("dd-MM-yyyy");

            String formatedDate = "";
//            LocalDate orgDate = record.getCell(4).getLocalDateTimeCellValue().toLocalDate();
//            log.info("--------Original Date", orgDate);
//            try {
//                Date date =sdf.parse(String.valueOf(orgDate));
//                formatedDate = required.format(date);
////                log.info("------extracted date", formatedDate);
//            }catch (ParseException e){
//                System.out.println("Error parsing date" + e.getMessage());
//            }


            Cell dateCell = record.getCell(4);
//            if(dateCell != null){
//                LocalDate orgDate = dateCell.getLocalDateTimeCellValue().toLocalDate();
//                try {
//                    Date date =sdf.parse(String.valueOf(orgDate));
//                    formatedDate = required.format(date);
//                    entity.setPaymentDate(formatedDate);
//                }catch(Exception e){
//                    System.out.println();
//                }
//            }
            Cell amountCell = record.getCell(5);
            if (amountCell != null && amountCell.getCellType() == CellType.NUMERIC) {
                double amountValue = amountCell.getNumericCellValue();
                entity.setAmount(amountValue);
            } else {
                // Handle the case when the cell is null or not a numeric value
                log.warn("Invalid or missing numeric value in the cell at row " + record.getRowNum() + ", column 5");
                // You might want to set a default value or handle it in another way
            }

            Cell itemDesc = record.getCell(2);
            if(itemDesc != null && itemDesc.getCellType() == CellType.STRING){
                String itemValue = itemDesc.getStringCellValue();
                entity.setSubItemDescription(itemValue);
            }
            Cell voucherVal = record.getCell(0);
            if(voucherVal != null && voucherVal.getCellType() == CellType.NUMERIC){
                double itemValue = voucherVal.getNumericCellValue();
                entity.setVoucher((int) itemValue);
            }
            Cell payee = record.getCell(3);
            if(payee != null && payee.getCellType() == CellType.STRING){
                String itemValue = payee.getStringCellValue();
                entity.setPayyee(itemValue);
            }
            Cell AcsDetails = record.getCell(1);
            if(AcsDetails != null && AcsDetails.getCellType() == CellType.STRING){
                String itemValue = AcsDetails.getStringCellValue();
                entity.setAccountDetails(itemValue);
            }
            Cell statuses = record.getCell(1);
            if(statuses != null && statuses.getCellType() == CellType.STRING){
                if(record.getCell(1).getStringCellValue().length() >38){
                    String splitline = statuses.getStringCellValue().substring(38,45);
                    entity.setStatus(splitline);
                }else {
                    entity.setStatus("");
                }
            }


//            splitline.split()


            DataFormatter dataFormatter = new DataFormatter();

            recurrent.add(entity);

        }
        log.info("-------done reading");
        log.info("----------Data size" + recurrent.size());

        workbook.close();
        file.close();
//        ifmisRepo.save(transactions)
        return  recurrent;

    }
    public List<Governor> readDevelopment(String filePath) throws IOException {
        log.info("-----Initializing Reading----",filePath);
        List<Governor> development = new ArrayList<>();

        File newFile = new File(filePath);
        if (!newFile.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        log.info("-----reading----");
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Integer rowNum = 0;
        for (Row record : sheet) {
            if (record.getRowNum() == 0) {
                continue;
            }
//            log.info("-----processing at row" + rowNum++);
            Governor entity = new Governor();

            //format the date to required date pattern
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
            SimpleDateFormat required = new SimpleDateFormat("dd-MM-yyyy");

            String formatedDate = "";
//            LocalDate orgDate = record.getCell(4).getLocalDateTimeCellValue().toLocalDate();
//            log.info("--------Original Date", orgDate);
//            try {
//                Date date =sdf.parse(String.valueOf(orgDate));
//                formatedDate = required.format(date);
////                log.info("------extracted date", formatedDate);
//            }catch (ParseException e){
//                System.out.println("Error parsing date" + e.getMessage());
//            }


            Cell dateCell = record.getCell(4);
//            if(dateCell != null){
//                LocalDate orgDate = dateCell.getLocalDateTimeCellValue().toLocalDate();
//                try {
//                    Date date =sdf.parse(String.valueOf(orgDate));
//                    formatedDate = required.format(date);
//                    entity.setPaymentDate(formatedDate);
//                }catch(Exception e){
//                    System.out.println();
//                }
//            }
            Cell amountCell = record.getCell(5);
            if (amountCell != null && amountCell.getCellType() == CellType.NUMERIC) {
                double amountValue = amountCell.getNumericCellValue();
                entity.setAmount(amountValue);
            } else {
                // Handle the case when the cell is null or not a numeric value
                log.warn("Invalid or missing numeric value in the cell at row " + record.getRowNum() + ", column 5");
                // You might want to set a default value or handle it in another way
            }

            Cell itemDesc = record.getCell(2);
            if(itemDesc != null && itemDesc.getCellType() == CellType.STRING){
                String itemValue = itemDesc.getStringCellValue();
                entity.setSubItemDescription(itemValue);
            }
            Cell voucherVal = record.getCell(0);
            if(voucherVal != null && voucherVal.getCellType() == CellType.NUMERIC){
                double itemValue = voucherVal.getNumericCellValue();
                entity.setVoucher((int) itemValue);
            }
            Cell payee = record.getCell(3);
            if(payee != null && payee.getCellType() == CellType.STRING){
                String itemValue = payee.getStringCellValue();
                entity.setPayyee(itemValue);
            }
            Cell AcsDetails = record.getCell(1);
            if(AcsDetails != null && AcsDetails.getCellType() == CellType.STRING){
                String itemValue = AcsDetails.getStringCellValue();
                entity.setAccountDetails(itemValue);
            }
            Cell statuses = record.getCell(1);
            if(statuses != null && statuses.getCellType() == CellType.STRING){
                if(record.getCell(1).getStringCellValue().length() >38){
                    String splitline = statuses.getStringCellValue().substring(38,45);
                    entity.setStatus(splitline);
                }else {
                    entity.setStatus("");
                }
            }
//            entity.se


//            splitline.split()


            DataFormatter dataFormatter = new DataFormatter();

            development.add(entity);

        }
        log.info("-------done reading");
        log.info("----------Data size" + development.size());

        workbook.close();
        file.close();
        return  development;
    }

}
