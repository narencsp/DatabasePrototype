import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.FileReader;
import java.util.Iterator;
public class UserLogin {

    private String userName, password;


    public void getUsername() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Username");
        userName = in.nextLine();
        System.out.println("Enter Password");
        password = in.nextLine();

        Boolean result = AuthenticateUser(userName, password);
        if(result.equals(true))
            System.out.println("User found in Table");
        else
            System.out.println("User not found in Table");
    }

    public Boolean AuthenticateUser(String userName, String password)  {
        Boolean value=false;
        String line = "";
        String splitBy = ",";
        try
        {

            BufferedReader br = new BufferedReader(new FileReader("/home/narendran/IdeaProjects/csci-5408-w2021-project-group15/DDBMS/src/com.package.presentation/users.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] users = line.split(splitBy);
                if(users[0].equals(userName) && users[1].equals(password)){
                    return true;
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

}
