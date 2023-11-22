package com.example.firstcrud.GovernorMinistry.FileReaders;

import com.example.firstcrud.GovernorMinistry.Models.Governor;
import com.example.firstcrud.GovernorMinistry.Repository.GovernorRepo;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IteratorReader {
    @Autowired
    private GovernorRepo governorRepo;

    public DataStorageGov readRec(String path){
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
//            Workbook workbook = path.endsWith(".xlsx") ? WorkbookFactory.create(fileInputStream) : new HSSFWorkbook(fileInputStream)){}
            List<Governor> list = new ArrayList<>();
            Workbook workbook;
            if(path.endsWith(".xlsx")){
                workbook = WorkbookFactory.create(fileInputStream);
            } else {
                workbook = new HSSFWorkbook(fileInputStream);
            }
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row>  rowIterator = sheet.iterator();
            //skip first row
            for (int i=0; i<0; i++){
                rowIterator.hasNext();
            }
//            for(int i; i<)
            Row row = rowIterator.next();
//            String[] columnNames =
            int columnCount = row.getLastCellNum();
            String[] columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = row.getCell(i).getStringCellValue();
            }
            List<Governor> listRec = new ArrayList<>();
            while (rowIterator.hasNext()){
                row = rowIterator.next();
                Governor governor = new Governor();
                governor.setAccountDetails(row.getCell(1).getStringCellValue());
                governor.setAmount(row.iterator().next().getNumericCellValue());
//                governor.setPayyee(row.getCell(3).getStringCellValue());
                governor.setPayyee(convertToNumeric(row,3));
                governor.setAmount(Double.parseDouble(convertToNumeric(row,5)));
                governor.setPaymentDate(parseDate(row.getCell(5).getStringCellValue()));
                list.add(governor);
                governorRepo.saveAll(list);


            }
            return (DataStorageGov) list;

        }catch (Exception e){
            return null;
        }

    }

    public static Date parseDate(String dateValue){
        if(dateValue.isEmpty()){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parsedDate = sdf.parse(dateValue);
            return parsedDate;
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }
//        return null;
    }

    public String convertToNumeric(Row row, Integer cellNo){
        try {
            String value;
            DecimalFormat decimalFormat = new DecimalFormat("0");
            if(row.getCell(cellNo) != null){
                if(row.getCell(cellNo).getCellType() == CellType.NUMERIC){
                    double numericValue = row.getCell(cellNo).getNumericCellValue();
                    value = String.valueOf(decimalFormat.format(numericValue));
                } else {
                    value = row.getCell(cellNo).getStringCellValue();
                }
                return value;
            } else {
                return "";
            }
        }catch (Exception e){
            return null;
        }
    }

    public String extractMinitry(String input){
        String[] voucher = input.split("-");
        String ministry = voucher[voucher.length];
        if(ministry.length() > 38){
            return ministry.substring(38,45);
        }
       return null;
    }

    public static void main(String[] args){
        String path = "/home/tm/Documents/Recurrent.xlsx";
        IteratorReader iteratorReader = new IteratorReader();
        List<Governor> list = (List<Governor>) iteratorReader.readRec(path);


    }

}
