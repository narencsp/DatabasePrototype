package iooperations;

import java.io.File;
import java.io.FileWriter;


public class CreateDatabase {
    public String createDatabase(String dbName){
        String result = null;
        try {
            File file = new File("src/com/package/database/"+dbName+".txt");
            if(!file.exists()){
                FileWriter fileWriter = new FileWriter("src/com/package/database/"+dbName+".txt");
                if(fileWriter!=null){
                    fileWriter.append("#DATABASE\n@name\n"+dbName+"\n@table");
                    result = "Database Created Successfully";
                }
                else{
                    result = "Database not created";
                }
            fileWriter.flush();
            fileWriter.close();
            }
            else{
                result = "Database already exists";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
