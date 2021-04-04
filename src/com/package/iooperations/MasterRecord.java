package iooperations;

import java.io.File;
import java.io.FileWriter;

public class MasterRecord {

    public String writeMasterRecord(String dbName, String tableName){
        String response = null;
        try {
            File file = new File("src/com/package/Master/master.txt");
            if(!file.exists()){
                FileWriter fileWriter = new FileWriter(file,true);
                while(fileWriter!=null){
                    if(fileWriter.equals(dbName)){

                    }
                    else{

                    }
                    fileWriter.append("#DATABASE\n@name\n"+dbName+"\n@table");
                    response = "Database Created Successfully";
                }
                fileWriter.flush();
                fileWriter.close();
            }
            else{
                response = "Master Record not found";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public String deleteFromMasterRecord(String dbName, String tableName){
        String response = null;

        return response;
    }

}
