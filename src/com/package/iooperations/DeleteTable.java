package iooperations;

import java.io.*;

public class DeleteTable {
    String result=null;

    public String deleteTable(String tableToDelete, String location){

        if(location.equalsIgnoreCase("local")){
            try
            {
                File file = new File("src/com/package/tables/"+tableToDelete+".txt");
                if(file.exists()){
                    boolean success = (new File("src/com/package/tables/"+tableToDelete)).delete();

                    if (success) {
                        result="Table deleted successfully";
                    }
                    else{
                        result = "Table not deleted";
                    }

    public String deleteTable(String tableToDelete, String database, String location){
        try
        {
            File file = new File("src/com/package/tables/"+tableToDelete+".txt");
            if(file.exists()){
                boolean success = (new File("src/com/package/tables/"+tableToDelete)).delete();

                if (success) {
                    result="Table deleted successfully";

                }
                else{

                    result = "Table doesn't exist";
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else{

        }
        queryLog(tableToDelete,location,result);
        return result;
    }

    private void queryLog(String tableName, String location, String response) {
        try {
            File file = new File("src/com/package/LOG/querylog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append(tableName+"\t"+location+"\t"+response+"\t->DELETE TABLE"+"\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
