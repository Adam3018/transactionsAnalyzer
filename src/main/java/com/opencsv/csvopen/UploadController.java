package com.opencsv.csvopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

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
    Map<String,List<Integer>> hashMapTransactions = new HashMap<>();
    HashSet<Integer> hashTransactionValues = new HashSet<Integer>();
    public CsvOriginal csv;
    ArrayList<CsvOriginal> csvList = new ArrayList<CsvOriginal>();
    
    @GetMapping("/index")
    public String aa(String aaa){
        return "aaaa";
    }

    @PostMapping("/upload")
    public Transaction uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) throws Exception{
        try {
            
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line="";
            
            while((line = br.readLine()) != null){
                String[] values = line.split(",");

                csvList.add(new CsvOriginal(values[0], values[1], values[2]));
                csv= new CsvOriginal(values[0], values[1], values[2]);

                if(hashMapTransactions.containsKey(values[2]))
                hashMapTransactions.get(values[2]).add(Integer.parseInt(values[1]));
                else
                {
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(values[1]));
                hashMapTransactions.put(values[2], list);
                }

                hashTransactionValues.add(Integer.parseInt(values[1]));
                

                profitLoss= profitLoss+Integer.parseInt(values[1]);
                

                Date simpleDate=new SimpleDateFormat("dd-MM-yyyy").parse(values[0]);
                Calendar cal=new GregorianCalendar();
                cal.setTime(simpleDate);

                int month=cal.get(Calendar.MONTH)+1;

                switch (month) {
                    case 1:  transNumJan++;
                             break;
                    case 2:  transNumFeb++;
                             break;
                    case 3:  transNumMar++;
                             break;
                    case 4:  transNumApr++;
                             break;
                    case 5:  transNumMay++;
                             break;
                    case 6:  transNumJun++;
                             break;
                    case 7:  transNumJul++;
                             break;
                    case 8:  transNumAvg++;
                             break;
                    case 9:  transNumSep++;
                             break;
                    case 10: transNumOct++;
                             break;
                    case 11: transNumNov++;
                             break;
                    case 12: transNumDec++;
                             break;
                }
                rowNum++;
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        
        List<Integer> listValues = new ArrayList<Integer>(hashTransactionValues);
        Collections.sort(listValues);

        

        String s=hashMapTransactions.toString();

        // String rtrn = "Number of rows: "+rowNum+
        // "\n\nTop 3 expenses: "+listValues.get(0)+"\n"+listValues.get(1)+"\n"+listValues.get(2)+
        // "\n\nExpense per category: "+s+
        // "\n\nProfit/loss: "+profitLoss+
        // "\n\nTransactions per month:\r\nJan "+transNumJan+
        // "\nFeb "+transNumFeb+
        // "\nMar "+transNumMar+
        // "\nApr "+transNumApr+
        // "\nMay "+transNumMay+
        // "\nJun "+transNumJun+
        // "\nJul "+transNumJul+
        // "\nAvg "+transNumAvg+
        // "\nSep "+transNumSep+
        // "\nOct "+transNumOct+
        // "\nNov "+transNumNov+
        // "\nDec "+transNumDec;

        return new Transaction(rowNum, listValues.get(0), listValues.get(1), listValues.get(2),s,
         profitLoss, transNumJan, transNumFeb, transNumMar, transNumApr, transNumMay, transNumJun, transNumJul, transNumAvg, transNumSep
         , transNumOct, transNumNov, transNumDec);

        
        //return csvList;


        
    }

   
    
}
