package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MasterRecord {

    public void writeMasterRecord(String tableName, String location) throws Exception{
        String line = "";
        String splitBy = "\n";
        BufferedReader br = new BufferedReader(new FileReader("src/com/package/MASTER/master.txt"));
        List<String> addTable = new ArrayList<>();
        while ((line = br.readLine()) != null)
        {
            String[] users = line.split(splitBy);
            addTable.add(users[0]);
        }

        FileWriter  fileWriter = new FileWriter("src/com/package/MASTER/master.txt");
        for(int i=0;i<addTable.size();i++){
            if(location.contains("local")&&i==2){
                fileWriter.append(addTable.get(i)+"~"+tableName+"\n");
            }
            else if(i==4 &&location.contains("remote")){
                fileWriter.append(addTable.get(i)+"~"+tableName+"\n");
            }
            else{
                fileWriter.append(addTable.get(i)+"\n");
            }

        }
        fileWriter.flush();
        fileWriter.close();
        queryLog(tableName,location,"Table Added");
    }

    public void deleteFromMasterRecord( String tableName,String location) throws Exception{
        String line = "";
        String splitBy = "\n";
        BufferedReader br = new BufferedReader(new FileReader("src/com/package/MASTER/master.txt"));
        List<String> removeTable = new ArrayList<>();
        while ((line = br.readLine()) != null)
        {
            String[] users = line.split(splitBy);
            removeTable.add(users[0]);
        }

       FileWriter  fileWriter = new FileWriter("src/com/package/MASTER/master.txt");
        for(int i=0;i<removeTable.size();i++){
            if(location.contains("local")&&i==2){
                fileWriter.append((removeTable.get(i).replace(tableName,"")));
            }
            else if(i==4 &&location.contains("remote")){
                fileWriter.append((removeTable.get(i).replace(tableName,"")));
            }
            else{
                fileWriter.append(removeTable.get(i)+"\n");
            }

        }
        fileWriter.flush();
        fileWriter.close();
        queryLog(tableName,location,"Table Deleted");

    }


    private void queryLog(String tableName, String location, String response) {
        try {
            File file = new File("src/com/package/LOG/eventlog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append(tableName+"\t"+location+"\t"+response+"\t\t->###MASTER RECORD UPDATED"+"\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
