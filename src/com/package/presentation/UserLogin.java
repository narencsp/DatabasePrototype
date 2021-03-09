package presentation;

import java.io.*;

import java.util.Scanner;

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

            BufferedReader br = new BufferedReader(new FileReader("/home/narendran/IdeaProjects/csci-5408-w2021-project-group15/src/com/package/presentation/users.csv"));
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
