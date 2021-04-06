package iooperations;

import java.io.*;
import java.util.*;

public class ReadTable {
    String joiningValues = "";

    public Map<String,List<String>> readTableValues(String tableName) throws IOException {
        String line = "";
        String splitBy = "\n";
        int temp =0;

        ArrayList<String> tableValues = new ArrayList<>();
        Map<String,List<String>> readTableMap = new HashMap<>();
        List<List<String>> rowKeeper = new ArrayList<>();
        List<String> finalValues = new ArrayList<>();
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
                    List<String> db = new ArrayList<>();
                    db.add(i);
                    readTableMap.put("database",db);
                }
                else if(temp ==4){
                    readTable.put("table", i);
                    List<String> tabel = new ArrayList<>();
                    tabel.add(i);
                    System.out.println(i);
                    readTableMap.put("table",tabel);
                }
                else if(temp ==6){
                    readTable.put("column", i);
                    List<String> columns = Arrays.asList(i.split("~"));
                    readTableMap.put("column",columns);
                }
                else if(temp ==8){
                    readTable.put("meta", i);
                    List<String> metas = Arrays.asList(i.split("~"));
                    readTableMap.put("meta",metas);
                }
                else if(temp>9){
                    valueField.add(i);
                    List<String> rows = Arrays.asList(i.split("~"));
                    rowKeeper.add(rows);
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

            for(List<String> row : rowKeeper){
                for(String value : row){
                    finalValues.add(value);
                }
            }
        readTable.put("value",joiningValues);
        readTableMap.put("value",finalValues);

        return readTableMap;
    }

}
