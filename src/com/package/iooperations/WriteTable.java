package iooperations;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class WriteTable {

    public String insertIntoTable(String tableName, List<String> columnValues, String location) throws Exception {
        int temp =1;
        String response=null;
        if(location.equalsIgnoreCase("local")){
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
                }
                fileWriter.flush();
                fileWriter.close();
            }
            else{
                response = "Table doesn't exist";
            }
        }
        else{

        }
        queryLog(tableName,columnValues,location,response);
        return response;
    }

    private void queryLog(String tableName, List<String> context,String location,String response) {

        String temp ="";
        for(String i : context){
            temp+=i+"~";
        }
        try {
            File file = new File("src/com/package/LOG/querylog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append(tableName+"\t"+location+"\t"+temp+"\t"+response+"\t->Write Operation"+"\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
