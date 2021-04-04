package iooperations;

import java.io.*;
import java.util.List;

public class CreateTable {
    public String createTable(String tablename, String dbName, List<String> columnNames){
        String result = null;
        int temp=1;
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
        return result;
    }
}
