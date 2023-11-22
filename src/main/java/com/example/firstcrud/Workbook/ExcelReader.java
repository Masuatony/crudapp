package com.example.firstcrud.Workbook;

import com.example.firstcrud.Workbook.DataPesister.DataPersister;
import com.example.firstcrud.Workbook.Entity.Ifmis;
import com.example.firstcrud.Workbook.IfmisRepo.IfmisRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@Slf4j
public class ExcelReader {
    String dir = "";
    @Autowired
    private DataPersister persister;

    @Autowired
    private IfmisRepo ifmisRepo;

    public List<Ifmis> read(String filePath) throws IOException {
        log.info("-----Initializing Reading----",filePath);
        List<Ifmis> transactions = new ArrayList<>();

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
            if (record.getRowNum() == 0 ) {
                continue;
            }
//            log.info("-----processing at row" + rowNum++);
            Ifmis entity = new Ifmis();

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
            if(dateCell != null){
                LocalDate orgDate = dateCell.getLocalDateTimeCellValue().toLocalDate();
                try {
                    Date date =sdf.parse(String.valueOf(orgDate));
                    formatedDate = required.format(date);
                    entity.setPaymentDate(formatedDate);
                }catch(Exception e){
                    System.out.println();
                }
            }
//            CellType cellType = record.getCell(5).getCellType();
//            if (cellType == CellType.NUMERIC) {
//                double numberValue = cellType.getNumericCellValue();
//
//                if (DateUtil.isCellDateFormatted(cell)) {
//                    Date javaDate= DateUtil.getJavaDate(numberValue);
//                    //use DateFormat or as it is
//                    myObj.setValueFromCell(javaDate);
//                } else {
//                    // Use BigDecimal to avoid  Scientific numbers like 1.2345678E9
//                    String stringCellValue =  BigDecimal.valueOf(numberValue).toPlainString();
//                    myObj.setValue(stringCellValue);
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

            transactions.add(entity);

        }
        log.info("-------done reading");
        log.info("----------Data size" + transactions.size());

        workbook.close();
        file.close();
//        ifmisRepo.save(transactions)
        return  transactions;

    }
//    public Ifmis OnBoard(String filePath) throws IOException {
//        Ifmis data = read(filePath);
//        persister.insertValue((List<Ifmis>) data);
//        log.info("----Records being saved: {}", ifmisRepo.getSizeOfdata());
//        return data;
//    }

}
