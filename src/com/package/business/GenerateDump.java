package business;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateDump {
    public void dumpGenerator(String location)  throws  Exception{

        File folder = new File("src/com/package/TABLES");
        String result = null;
        if (location.equalsIgnoreCase("local")) {
            if (folder.exists()) {
                folder = new File("src/com/package/TABLES/local");
            } else {
                result = "Database table not found";
            }
        } else if(location.equalsIgnoreCase("remote")){
            if (folder.exists()) {
                folder = new File("src/com/package/TABLES/remote");
            } else {
                result = "Database table not found";
            }
        }

        File[] listOfFiles = folder.listFiles();
        List<String> fileNames = new ArrayList<>();
        String line = "";
        String splitBy = "\n";
        BufferedReader br=null;
        FileWriter fileWriter = new FileWriter("src/com/package/DUMP/dump.txt");
        List<String> tableValues;
        String tempValue="";

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileNames.add(listOfFiles[i].getName());
            }
        }
        for(int i=0;i<fileNames.size();i++){
             tableValues = new ArrayList<>();
             br = new BufferedReader(new FileReader("src/com/package/TABLES/"+fileNames.get(i)));
            while ((line = br.readLine()) != null)
            {
                String[] users = line.split(splitBy);
                tableValues.add(users[0]);
            }

            tempValue+=fileNames.get(i)+"\n";
            for(int j=0;j<tableValues.size();j++){
                tempValue+=tableValues.get(j)+"\n";
            }
            tempValue+="\n";

        }
        fileWriter.append(tempValue+"\n");
        fileWriter.flush();
        fileWriter.close();
        queryLog();
    }


    private void queryLog() {
        try {
            File file = new File("src/com/package/LOG/generallog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append("###DUMP Generated###"+"\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
