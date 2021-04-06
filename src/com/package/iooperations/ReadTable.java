package iooperations;

import java.io.*;
import java.util.*;

public class ReadTable {
    String joiningValues = "";

    public Map readTableValues(String tableName) throws IOException {
        String line = "";
        String splitBy = "\n";
        int temp =0;

        ArrayList<String> tableValues = new ArrayList<>();
        Map<String, String> readTable = new LinkedHashMap<>();
        ArrayList<String> valueField = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/com/package/tables/"+tableName+".txt"));
            while ((line = br.readLine()) != null)
            {
                String[] users = line.split(splitBy);
                tableValues.add(users[0]);
            }


            for(String i : tableValues){

                if(temp == 2){
                    readTable.put("database", i);
                }
                else if(temp ==4){
                    readTable.put("table", i);
                }
                else if(temp ==6){
                    readTable.put("column", i);
                }
                else if(temp ==8){
                    readTable.put("meta", i);
                }
                else if(temp>9){
                    valueField.add(i);

                }
                temp++;
            }

            temp=1;
            for(String str : valueField){
                if(valueField.size()==temp){

                    joiningValues = joiningValues+str;
                }
                else{
                    joiningValues = joiningValues+str+"\n";
                }
                temp++;
            }
        readTable.put("value",joiningValues);



        return readTable;
    }

}
