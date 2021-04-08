package persistence;

import business.TableLock;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteTable {
    String result = null;

    public String deleteTable(String tableToDelete, String database, String location) throws Exception {

        TableLock tableLock = new TableLock();
        tableLock.isTableLocked(tableToDelete);
        File file = new File("src/com/package/DATABASE/" + database + ".txt");

        if (location.equalsIgnoreCase("local")) {
            if (file.exists()) {
                file = new File("src/com/package/TABLES/local/" + tableToDelete + ".txt");
            } else {
                result = "Database table not found";
            }
        } else if(location.equalsIgnoreCase("remote")){
            if (file.exists()) {
                file = new File("src/com/package/TABLES/remote/" + tableToDelete + ".txt");
            } else {
                result = "Database table not found";
            }
        }
        try {
            if (file.exists()) {
                boolean success = (file).delete();

                if (success) {
                    result = "Table deleted successfully";
                } else {
                    result = "Table doesn't exist";
                }
                deleteFromDatabase(database, tableToDelete);
                deletefromForeignKeyReferences(tableToDelete);
                MasterRecord masterRecord = new MasterRecord();
                masterRecord.deleteFromMasterRecord(tableToDelete, location);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        queryLog(database, tableToDelete, location, result);
        Thread.sleep(12000);
        tableLock.clearLock(tableToDelete);
        return result;

    }

    public void deletefromForeignKeyReferences(String tableToDelete) throws Exception {
        String line = "";
        String splitBy = "\n";
        BufferedReader br = new BufferedReader(new FileReader("src/com/package/MASTER/ForeignKeyReferences.txt"));
        List<String> removeTableLines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] users = line.split(splitBy);
            removeTableLines.add(users[0]);
        }
        for (int i = 0; i < removeTableLines.size(); i++) {


            if (removeTableLines.get(i).contains(tableToDelete)) {
                removeTableLines.remove(i);
            }
        }
        FileWriter fileWriter = new FileWriter("src/com/package/MASTER/ForeignKeyReferences.txt");
        for (int i = 0; i < removeTableLines.size(); i++) {
            fileWriter.append(removeTableLines.get(i) + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public void deleteFromDatabase(String dbName, String tableToDelete) throws Exception {
        String line = "";
        String splitBy = "\n";
        BufferedReader br = new BufferedReader(new FileReader("src/com/package/DATABASE/" + dbName + ".txt"));
        List<String> removeTableLines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] users = line.split(splitBy);
            removeTableLines.add(users[0]);
        }
        for (int i = 0; i < removeTableLines.size(); i++) {
            if (removeTableLines.get(i).contains(tableToDelete)) {
                removeTableLines.remove(i);
            }
        }
        FileWriter fileWriter = new FileWriter("src/com/package/DATABASE/" + dbName + ".txt");
        for (int i = 0; i < removeTableLines.size(); i++) {
            fileWriter.append(removeTableLines.get(i) + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    private void queryLog(String database, String tableName, String location, String response) {
        try {
            File file = new File("src/com/package/LOG/eventlog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append(database + "\t" + tableName + "\t" + location + "\t" + response + "\t->DELETE TABLE" + "\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
