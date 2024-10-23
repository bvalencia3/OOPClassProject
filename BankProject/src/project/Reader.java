package BankProject.src.project;

import java.io.*;
import java.util.*;


public class Reader {
    public static void main(String[] args) {

  
 
        String file = "OOPClassProject\\BankProject\\src\\project\\CS 3331 - Bank Users.csv";

        BufferedReader reader = null;
        String line;
        ArrayList<String[]> bankInfo = new ArrayList<String[]>();  
  
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {

                String[] values  = line.split(",");
                bankInfo.add(values);

            
                     
            
               }
            for(String i[] : bankInfo ){
                System.out.println(Arrays.toString(i));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            
        }
    }

}
