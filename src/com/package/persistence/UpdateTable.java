package persistence;

import business.TableLock;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class UpdateTable {


    public String updateOrDelete(String tableName, String databaseName , String location, Map<String, List<String>> tableValues) throws Exception {
        String response = null;


        TableLock tableLock = new TableLock();
        //tableLock.isTableLocked(tableName);
        File file = new File("src/com/package/DATABASE/" + databaseName + ".txt");

        if (location.equalsIgnoreCase("local")) {
            if (file.exists()) {
                file = new File("src/com/package/TABLES/local/" + tableName + ".txt");
            } else {
                response = "Database table not found";
            }
        } else if(location.equalsIgnoreCase("remote")){
            if (file.exists()) {
                file = new File("src/com/package/TABLES/remote/" + tableName + ".txt");
            } else {
                response = "Database table not found";
            }
        }
        try {
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file);
                if (fileWriter != null) {

                    fileWriter.append("#TABLE\n@database\n");
                    fileWriter.append(tableValues.get("database").get(0));
                    fileWriter.append("\n@table\n");
                    fileWriter.append(tableValues.get("table").get(0));
                    fileWriter.append("\n@column\n");
                    fileWriter.append(getColumnNames(tableValues.get("column")));
                    fileWriter.append("\n@meta\n");
                    fileWriter.append(getColumnNames(tableValues.get("meta")));
                    fileWriter.append("\n@value\n");
                    fileWriter.append(getValue(tableValues.get("value"), tableValues.get("column")));
                    response = "Values Changed Successfully";
                } else {
                    response = "Data not inserted";
                }
                fileWriter.flush();
                fileWriter.close();
            } else {
                response = "Table doesn't exist";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        queryLog(tableValues, location, response);
        //Thread.sleep(8000);
        //tableLock.clearLock(tableName);

        return response;
    }

    public String getColumnNames(List<String> column) {
        StringBuilder columnStr = new StringBuilder();
        for (int i = 0; i < column.size(); i++) {
            if (i == (column.size() - 1)) {
                columnStr.append(column.get(i));
            } else {
                columnStr.append(column.get(i) + "~");
            }
        }
        return columnStr.toString();
    }

    public String getValue(List<String> value, List<String> column) {
        int columnSize = column.size();
        StringBuilder finalValue = new StringBuilder();
        for (int i = 1; i <= value.size(); i++) {

            if (i % columnSize == 0) {
                finalValue.append(value.get(i - 1) + "\n");
            } else {
                finalValue.append(value.get(i - 1) + "~");
            }
        }
        return finalValue.toString();
    }


    private void queryLog(Map<String, List<String>> tableValues, String location, String response) {

        String temp = "";
        temp += tableValues.get("database") + "\t";
        temp += tableValues.get("table") + "\t";
        temp += getColumnNames(tableValues.get("column")) + "\t";
        temp += getColumnNames(tableValues.get("meta")) + "\t";
        temp += getValue(tableValues.get("value"), tableValues.get("column"));


        try {
            File file = new File("src/com/package/LOG/eventlog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append(temp + "\t" + location + "\t" + response + "\t->UPDATE TABLE" + "\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
