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
        Map<String, String> c = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("src/com/package/tables/"+tableName+".txt"));
        while ((line = br.readLine()) != null)
        {
                String[] users = line.split(splitBy);
                c.put(users[0],users[1]);
        }
//

        return c;
    }

}
