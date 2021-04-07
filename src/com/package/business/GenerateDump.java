package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class GenerateDump {
    public void dumpGenerator()  throws  Exception{

        File folder = new File("src/com/package/TABLES");
        File[] listOfFiles = folder.listFiles();
        List<String> fileNames = new ArrayList<>();
        String line = "";
        String splitBy = "\n";
        BufferedReader br=null;
        File file = new File("src/com/package/DUMP/dump.txt");
        FileWriter fileWriter=null;

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileNames.add(listOfFiles[i].getName());
            }
        }
        for(int i=0;i<fileNames.size();i++){
            List<String> tableValues = new ArrayList<>();

             br = new BufferedReader(new FileReader("src/com/package/TABLES/"+fileNames.get(i)));
            while ((line = br.readLine()) != null)
            {
                String[] users = line.split(splitBy);
                tableValues.add(users[0]);
            }
             fileWriter = new FileWriter(file);
            for(i=0;i<tableValues.size();i++){
                if(i>2 && i<9){
                    fileWriter.append(tableValues.get(i)+"\n");
                }
            }
            fileWriter.append("\n");


        }
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
