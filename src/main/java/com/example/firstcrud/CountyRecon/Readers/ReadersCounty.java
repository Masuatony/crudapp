package com.example.firstcrud.CountyRecon.Readers;

import com.example.firstcrud.CountyRecon.Model.County;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ReadersCounty {
    String recPath = "/home/tm/Documents/Recurrent.xlsx";
    String devPath = "/home/tm/Documents/devepment.xlsx";
    String depoPath = "/home/tm/Documents/Deposits.xlsx";

    public List<County> readRec() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(recPath);
        List<County> recur = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        log.info("------reading RECS-----");


        for (Row curRow : sheet) {
            if (curRow == null || curRow.getRowNum() == 0) {
                continue;
            }

            boolean isEmptyRow = true;
            for (Cell cell : curRow) {
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    isEmptyRow = false;
                    break;
                }
            }
            if (isEmptyRow) {
                continue;
            }
            County county = new County();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
//            try {
//                county.setPaymentDate(String.valueOf(sdf.parse(String.valueOf(curRow.getCell(4)))));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            county.setReason("Recurrent");
            recur.add(county);
//            log.info("---------saving--------");
            workbook.close();
            fileInputStream.close();
        }


        return recur;
    }

    public List<County> readDev() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(devPath);
        List<County> dev = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        log.info("------reading DEVS-----");


        for (Row curRow : sheet) {
            if (curRow == null || curRow.getRowNum() == 0) {
                continue;
            }

            boolean isEmptyRow = true;
            for (Cell cell : curRow) {
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    isEmptyRow = false;
                    break;
                }
            }
            if (isEmptyRow) {
                continue;
            }
            County county = new County();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
//            try {
//                county.setPaymentDate(String.valueOf(sdf.parse(String.valueOf(curRow.getCell(4)))));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            county.setReason("Development");
            dev.add(county);
            workbook.close();
            fileInputStream.close();
        }


        return dev;
    }

    public List<County> readDep() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(depoPath);
        List<County> depo = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        log.info("------reading deposits-----");

        for (Row curRow : sheet) {
            if (curRow == null || curRow.getRowNum() == 0) {
                continue;
            }

            boolean isEmptyRow = true;
            for (Cell cell : curRow) {
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    isEmptyRow = false;
                    break;
                }
            }
            if (isEmptyRow) {
                continue;
            }
            County county = new County();
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
//            try {
////                if()
//                county.setPaymentDate(String.valueOf(sdf.parse(String.valueOf(curRow.getCell(4)))));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            county.setReason("Deposits");
            county.setPayyee(String.format(curRow.getCell(3).getStringCellValue()));
            depo.add(county);
            workbook.close();
            fileInputStream.close();
            log.info("----------saving depo----------");
        }
        return depo;
    }

}


