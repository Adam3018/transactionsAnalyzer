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
    public int expense1;
    public int expense2;
    public int prev;
    public int prev1;
    public int expense3;
    public int transNumJan=0;
    public int transNumFeb=0;
    public int transNumMar=0;
    public int transNumApr=0;
    public int transNumMay=0;
    public int transNumJun=0;
    public int transNumJul=0;
    public int transNumAvg=0;
    public int transNumSep=0;
    public int transNumOct=0;
    public int transNumNov=0;
    public int transNumDec=0;
    String ts;
    String ts2;
    String ts3;
    
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

                int hlp=values[0].charAt(4);
                if(hlp==49)
                transNumJan++;
                else if(hlp==50)
                transNumFeb++;
                else if(hlp==51)
                transNumMar++;
                else if(hlp==52)
                transNumApr++;
                else if(hlp==53)
                transNumMay++;
                else if(hlp==54)
                transNumJun++;
                else if(hlp==55)
                transNumJul++;
                else if(hlp==56)
                transNumAvg++;
                else if(hlp==57)
                transNumSep++;
                else if(hlp==58)
                transNumOct++;
                else if(hlp==59)
                transNumNov++;
                else if(hlp==60)
                transNumDec++;


                if(expense1>Integer.parseInt(values[1]))
                {
                expense1=Integer.parseInt(values[1]);
                ts=expense1+" "+values[2];
                }
                if(expense2>Integer.parseInt(values[1]))
                {
                expense2=Integer.parseInt(values[1]);
                ts2=expense2+" "+values[2];
                }
                if(expense3>Integer.parseInt(values[1]))
                {
                expense3=Integer.parseInt(values[1]);
                ts3=expense3+" "+values[2];
                }
                if(expense2==expense1)
                expense2=prev;
                if(expense3==expense2 || expense3==expense1)
                expense3=prev1;
                
                prev=expense2;
                prev1=expense3;
                rowNum++;
                
            }
           
           
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        System.out.println(expense1+" "+expense2+" "+expense3);

        String rtrn = "Number of rows: "+rowNum+
        "\nProfit/loss: "+profitLoss+
        "\nNum1 expense: "+ts+
        "\nNum2 expense: "+ts2+
        "\nNum3 expense: "+ts3+
        "\nTransactions per month:\r\nJan "+transNumJan+
        "\nFeb "+transNumFeb+
        "\nMar "+transNumMar+
        "\nApr "+transNumApr+
        "\nMay "+transNumMay+
        "\nJun "+transNumJun+
        "\nJul "+transNumJul+
        "\nAvg "+transNumAvg+
        "\nSep "+transNumSep+
        "\nOct "+transNumOct+
        "\nNov "+transNumNov+
        "\nDec "+transNumDec;

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
