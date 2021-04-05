package iooperations;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadTable {
    public Map readTableValues(String tableName) throws IOException {
        String line = "";
        String splitBy = "\n";
        ArrayList<String> tableValues = new ArrayList<>();
        Map<String, List<String>> readTable = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("src/com/package/tables/"+tableName+".txt"));
        while ((line = br.readLine()) != null)
        {
                String[] users = line.split(splitBy);
                tableValues.add(users[0]);
                //readTable.put(users[0],users[1]);
        }
        for(String i : tableValues){
            System.out.println(i);
        }

        return readTable;
    }

}
