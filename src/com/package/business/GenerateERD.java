package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class GenerateERD {
    public void displayERD() throws Exception{
        String line = "";
        String splitBy = "~";
        BufferedReader br = new BufferedReader(new FileReader("src/com/package/MASTER/ForeignKeyReferences.txt"));
        List<String> tableValues = new ArrayList<>();
        while ((line = br.readLine()) != null)
        {
            String[] users = line.split(splitBy);
            tableValues.add(users[0]);
        }
        for(int i =0;i<tableValues.size();i++){
            if(i>1){
                System.out.println(tableValues.get(i));
            }
        }
        queryLog();
    }


    private void queryLog() {
        try {
            File file = new File("src/com/package/LOG/generallog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append("###ERD Generated###"+"\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
