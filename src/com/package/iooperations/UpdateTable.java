package iooperations;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

public class UpdateTable {

    public String updateOrDelete(String tableName, Map<String, String> tableValues){
        String response = null;

        try {
            File file = new File("src/com/package/tables/"+tableName+".txt");
            if(file.exists()){
                FileWriter fileWriter = new FileWriter(file);
                if(fileWriter!=null){

                    fileWriter.append("#TABLE\n@database\n");
                    fileWriter.append(tableValues.get("database"));
                    fileWriter.append("\n@table\n");
                    fileWriter.append(tableValues.get("table"));
                    fileWriter.append("\n@column\n");
                    fileWriter.append(tableValues.get("column"));
                    fileWriter.append("\n@meta\n");
                    fileWriter.append(tableValues.get("meta"));
                    fileWriter.append("\n@value\n");
                    fileWriter.append(tableValues.get("value"));
                    response = "Database Created Successfully";
                }
                else{
                    response = "Data not inserted";
                }
                fileWriter.flush();
                fileWriter.close();
            }
            else{
                response = "Table doesn't exist";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


}
