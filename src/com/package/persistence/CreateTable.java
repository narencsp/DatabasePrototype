package persistence;

import java.io.*;
import java.util.List;

public class CreateTable {


    public String createTable(String tablename, String dbName, String location, List<String> columnNames, List<String> columnType, List<String> foreignKey) throws Exception{

        String result = null;
        int temp=1;
        File file = new File("src/com/package/DATABASE/"+dbName+".txt");;
        if(location.equalsIgnoreCase("local")) {

            if (file.exists()) { //check if database file exists
                file = new File("src/com/package/TABLES/local/" + tablename + ".txt");
            }
            else{
                result = "Database table not found";
            }
        }
            else{
                if(file.exists()) { //check if database file exists
                    file = new File("src/com/package/TABLES/remote/" + tablename + ".txt");
                }
                else {
                    result = "Database table not found";
                }
            }
            try
            {

                    if(!file.exists()){
                        FileWriter  fileWriter = new FileWriter("src/com/package/DATABASE/"+dbName+".txt",true);
                        if(fileWriter!=null){
                            fileWriter.append("\n");
                            fileWriter.append(tablename);
                            fileWriter.flush();
                            fileWriter.close();
                            fileWriter = new FileWriter("src/com/package/TABLES/"+tablename+".txt");
                            if(fileWriter!=null){
                                fileWriter.append("#TABLE\n@database\n"+dbName+"\n@table\n"+tablename+"\n@column\n");
                                for(String column : columnNames){
                                    if(temp==(columnNames.size())){
                                        fileWriter.append(column);
                                    }
                                    else{
                                        fileWriter.append(column+"~");
                                    }

                                    temp++;
                                }
                                temp=1;
                                fileWriter.append("\n"+"@meta\n");
                                for(String column : columnType){
                                    if(temp==(columnType.size())){
                                        fileWriter.append(column);
                                    }
                                    else{
                                        fileWriter.append(column+"~");
                                    }

                                    temp++;
                                }
                                fileWriter.append("\n"+"@value");
                                result = "Inserted Successfully";
                            }
                            else{
                                result = "Error in table";
                            }
                        }
                        else{
                            result = "Error in database table";
                        }
                        fileWriter.flush();
                        fileWriter.close();

                       enterForeignKeyDetails(foreignKey);
                       MasterRecord masterRecord = new MasterRecord();
                       masterRecord.writeMasterRecord(tablename,location);
                    }
                    else{
                        result = "Table already exists";
                    }


            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            queryLog(tablename,dbName,columnNames,columnType,location,result, foreignKey);


        return result;
    }

    public void enterForeignKeyDetails(List<String> foreignKey) throws Exception{
        FileWriter fileWriter = new FileWriter("src/com/package/MASTER/ForeignKeyReferences.txt",true);
        String merge="";
        for(int i=0;i<foreignKey.size();i++){
            merge+=foreignKey.get(i)+"\t";
        }
        fileWriter.append(merge+"\n");
        fileWriter.flush();
        fileWriter.close();
    }

    private void queryLog(String tableName, String dbName,List<String> columnName, List<String> columnType,String location,String response, List<String> foreignKey) {

        String temp ="";
        for(String i : columnName){
            temp+=i+"~";
        }
        String temp1 ="";
        for(String i : columnType){
            temp1+=i+"~";
        }
        String temp2 ="";
        for(String i: foreignKey){
            temp2+=foreignKey+" ";
        }
        try {
            File file = new File("src/com/package/LOG/eventlog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append(dbName+"\t"+location+"\t"+tableName+"\t"+temp+"\t"+temp1+"\t"+temp2+"\t"+columnType+"\t"+response+"\t->Create Table"+"\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
