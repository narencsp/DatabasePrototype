package iooperations;

import java.io.*;

public class DeleteTable {
    String result=null;
    public String deleteTable(String tableToDelete){

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
            }
            else{
                result = "Table doesn't exist";
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
