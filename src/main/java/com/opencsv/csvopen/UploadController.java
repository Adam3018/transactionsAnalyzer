package com.opencsv.csvopen;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.opencsv.CSVParser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    public int rowNum;
    public int profitLoss;
    @GetMapping("/adam")

    public String index(){
        return "adam";
    }
    
    @PostMapping("/upload")
    
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) throws Exception{
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line="";
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                profitLoss= profitLoss+Integer.parseInt(values[1]);
                
                //System.out.println(values[1]);
                rowNum++;
            }
            System.out.println("Profit/loss= "+profitLoss);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        String rtrn = "Profit/loss: "+profitLoss+"\r\nNumber of rows: "+rowNum;
        System.out.println(rtrn);
        return rtrn;
    //     List<Transaction> transList = new ArrayList<>();
    //     InputStream inputStream=file.getInputStream();
    //    // CSVParserSettings setting=new CSVParserSettings();
    //     CSVParser parser = new CSVParser();
    //     List<Record> parseAll = ((Object) parser).parseAllRecords(inputStream);
    //     return null;

        // if (file.isEmpty()){
        //     model.addAttribute("message","Please select a CSV file to upload.");
        //     model.addAttribute("status", false);

        // }
        // else {
        //     try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
        //         CsvToBean<Transaction> csvToBean = new CsvToBeanBuilder(reader)
        //         .withType(Transaction.class)
        //         .withIgnoreLeadingWhiteSpace(true)
        //         .build();

        //         List<Transaction> trns = csvToBean.parse();

        //         model.addAttribute("transactions", trns);
        //         model.addAttribute("status",true);
        //     }
        //     catch (Exception ex){
        //         model.addAttribute("message","An error occured");
        //         model.addAttribute("status",false);
        //     }
        // }
        //return rowNum;
        
    }
    
}
