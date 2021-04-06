package iooperations;

import java.io.*;
import java.util.List;

public class CreateTable {


    public String createTable(String tablename, String dbName, String location, List<String> columnNames, List<String> columnType, List<String> blank_list){

        String result = null;
        int temp=1;
        if(location.equalsIgnoreCase("local")){
            try
            {
                File file = new File("src/com/package/database/"+dbName+".txt");
                if(file.exists()){ //check if database file exists
                    file = new File("src/com/package/tables/"+tablename+".txt");
                    if(!file.exists()){
                        FileWriter  fileWriter = new FileWriter("src/com/package/database/"+dbName+".txt",true);
                        if(fileWriter!=null){
                            fileWriter.append("\n");
                            fileWriter.append(tablename);
                            fileWriter.flush();
                            fileWriter.close();
                            fileWriter = new FileWriter("src/com/package/tables/"+tablename+".txt");
                            if(fileWriter!=null){
                                fileWriter.append("#TABLE\n@database\n"+dbName+"\n@table\n"+tablename+"\n@column\n");
                                for(String column : columnNames){
                                    if(temp==(columnNames.size())){
                                        fileWriter.append(column);
                                    }
                                    else{
                                        fileWriter.append(column+"~");
                                    }

                                    temp++;
                                }
                                temp=1;
                                fileWriter.append("\n"+"@meta\n");
                                for(String column : columnType){
                                    if(temp==(columnType.size())){
                                        fileWriter.append(column);
                                    }
                                    else{
                                        fileWriter.append(column+"~");
                                    }

                                    temp++;
                                }
                                fileWriter.append("\n"+"@value");
                                result = "Inserted Successfully";
                            }
                            else{
                                result = "Error in table";
                            }
                        }
                        else{
                            result = "Error in database table";
                        }
                        fileWriter.flush();
                        fileWriter.close();
                    }
                    else{
                        result = "Table already exists";
                    }

                }
                else{
                    result = "Database table not found";
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else{

        }
        queryLog(tablename,dbName,columnNames,columnType,location,result);
        return result;
    }


    private void queryLog(String tableName, String dbName,List<String> columnName, List<String> columnType,String location,String response) {

        String temp ="";
        for(String i : columnName){
            temp+=i+"~";
        }
        String temp1 ="";
        for(String i : columnType){
            temp1+=i+"~";
        }
        try {
            File file = new File("src/com/package/LOG/querylog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append(tableName+"\t"+location+"\t"+dbName+"\t"+temp+"\t"+columnType+"\t"+response+"\t->Create Table"+"\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
