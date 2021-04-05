package iooperations;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class WriteTable {

    public String insertIntoTable(String tableName, List<String> columnValues) throws Exception {
        int temp =1;
        String response=null;
        File file = new File("src/com/package/tables/"+tableName+".txt");
        if(file.exists()){
            FileWriter  fileWriter = new FileWriter("src/com/package/tables/"+tableName+".txt",true);
            if(fileWriter!=null){
                fileWriter.append("\n");
                for(String column : columnValues){
                    if(temp==(columnValues.size())){
                        fileWriter.append(column);
                    }
                    else{
                        fileWriter.append(column+"~");
                    }

                    temp++;
                }
            response = "Data inserted successfully";
            }
            else{
                response = "Error accessing the table";
                //call.logfunction("INSERT",columnValues,tableName, response);
            }
            fileWriter.flush();
            fileWriter.close();
        }
        else{
            response = "Table doesn't exist";
        }

        return response;
    }
}
