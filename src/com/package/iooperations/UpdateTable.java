package iooperations;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class UpdateTable {

/*
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
*/

    public String updateOrDelete(String tableName, Map<String, List<String>> tableValues){
        String response = null;

        try {
            File file = new File("src/com/package/tables/"+tableName+".txt");
            if(file.exists()){
                FileWriter fileWriter = new FileWriter(file);
                if(fileWriter!=null){

                    fileWriter.append("#TABLE\n@database\n");
                    fileWriter.append(tableValues.get("database").get(0));
                    fileWriter.append("\n@table\n");
                    fileWriter.append(tableValues.get("table").get(0));
                    fileWriter.append("\n@column\n");
                    fileWriter.append(getColumnNames(tableValues.get("column")));
                    fileWriter.append("\n@meta\n");
                    fileWriter.append(getColumnNames(tableValues.get("meta")));
                    fileWriter.append("\n@value\n");
                    fileWriter.append(getValue(tableValues.get("value"),tableValues.get("column")));
                    response = "Values Changed Successfully";
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

    public String getColumnNames(List<String> column){
        StringBuilder columnStr = new StringBuilder();
        for(int i=0;i<column.size();i++){
            if(i==(column.size()-1)){
                columnStr.append(column.get(i));
            }else{
                columnStr.append(column.get(i)+"~");
            }
        }
        return columnStr.toString();
    }

    public String getValue(List<String> value, List<String> column){
        int columnSize = column.size();
        StringBuilder finalValue = new StringBuilder();
        System.out.println(value.size());
        for(int i=1;i<=value.size();i++) {

            if(i % columnSize == 0){
                finalValue.append(value.get(i - 1)+"\n");
            }else{
                finalValue.append(value.get(i - 1)+"~");
            }
        }
        System.out.print(finalValue.toString());
        return finalValue.toString();
        }


}
