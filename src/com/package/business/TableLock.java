package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TableLock {


    public void isTableLocked(String tableName) throws Exception{
        String line = "";
        String response = null;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/com/package/TABLES/lock.txt"));
        FileWriter fileWriter=null;

                if(line.contains(tableName)){
                    System.out.println("The table is currently being accessed by another user, please wait!");
                    Thread.sleep(10000);
                    fileWriter = new FileWriter(new File("src/com/package/TABLES/lock.txt"));
                    if (fileWriter != null) {
                        fileWriter.append(tableName+"\n");
                    }
                }
                else{
                    fileWriter = new FileWriter(new File("src/com/package/TABLES/lock.txt"));
                    if (fileWriter != null) {
                        fileWriter.append(tableName+"\n");
                    }
                }




        fileWriter.flush();
        fileWriter.close();
        queryLog(tableName);
    }


    public void clearLock(String tableName) throws Exception{
        FileWriter fileWriter = new FileWriter(new File("src/com/package/TABLES/lock.txt"));
        fileWriter.append("");
        fileWriter.flush();
        fileWriter.close();
        queryLog(tableName);
    }

    private void queryLog( String tableName) {
        try {
            File file = new File("src/com/package/LOG/eventlog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append(tableName+"\t \t->TABLE LOCK INVOKED"+"\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
