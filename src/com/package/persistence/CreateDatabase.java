package persistence;

import java.io.File;
import java.io.FileWriter;


public class CreateDatabase {
    public String createDatabase(String dbName){
        String result = null;
        try {
            File file = new File("src/com/package/DATABASE/"+dbName+".txt");
            if(!file.exists()){
                FileWriter fileWriter = new FileWriter("src/com/package/DATABASE/"+dbName+".txt");
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
        queryLog(dbName, result);
        return result;
    }

    private void queryLog(String dbName, String response) {
        try {
            File file = new File("src/com/package/LOG/eventlog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append(dbName+"\t"+response+"\t->CREATE DATABASE"+"\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
