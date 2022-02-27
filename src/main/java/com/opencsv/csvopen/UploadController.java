package com.opencsv.csvopen;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

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

    @GetMapping("/adam")

    public String index(){
        return "aa";
    }

    @PostMapping("/upload-csv-file")

    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        if (file.isEmpty()){
            model.addAttribute("message","Please select a CSV file to upload.");
            model.addAttribute("status", false);

        }
        else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
                CsvToBean<Transaction> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Transaction.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

                List<Transaction> trns = csvToBean.parse();

                model.addAttribute("transactions", trns);
                model.addAttribute("status",true);
            }
            catch (Exception ex){
                model.addAttribute("message","An error occured");
                model.addAttribute("status",false);
            }
        }
        return "file-upload-status";
    }
    
}
