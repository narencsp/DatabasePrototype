package persistence;

import java.io.*;
import java.util.*;

public class ReadTable {


    public Map<String,List<String>> readTableValues(String tableName, String location) throws IOException {
        String line = "";
        String splitBy = "\n";
        int temp =0;

        ArrayList<String> tableValues = new ArrayList<>();
        Map<String,List<String>> readTableMap = new HashMap<>();
        List<List<String>> rowKeeper = new ArrayList<>();
        List<String> finalValues = new ArrayList<>();
        if(location.equalsIgnoreCase("local")){
            BufferedReader br = new BufferedReader(new FileReader("src/com/package/TABLES/"+tableName+".txt"));
            while ((line = br.readLine()) != null)
            {
                String[] users = line.split(splitBy);
                tableValues.add(users[0]);
            }


            for(String i : tableValues){

                if(temp == 2){

                    List<String> db = new ArrayList<>();
                    db.add(i);
                    readTableMap.put("database",db);
                }
                else if(temp ==4){

                    List<String> tabel = new ArrayList<>();
                    tabel.add(i);
                    System.out.println(i);
                    readTableMap.put("table",tabel);
                }
                else if(temp ==6){

                    List<String> columns = Arrays.asList(i.split("~"));
                    readTableMap.put("column",columns);
                }
                else if(temp ==8){

                    List<String> metas = Arrays.asList(i.split("~"));
                    readTableMap.put("meta",metas);
                }
                else if(temp>9){

                    List<String> rows = Arrays.asList(i.split("~"));
                    rowKeeper.add(rows);
                }
                temp++;
            }


            for(List<String> row : rowKeeper){
                for(String value : row){
                    finalValues.add(value);
                }
            }

            queryLog(tableName,location);
        }
        else{

        }


        return readTableMap;
    }


    private void queryLog( String tableName, String location) {
        try {
            File file = new File("src/com/package/LOG/eventlog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append(tableName+"\t"+location+"\t->READ OPERATION"+"\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
