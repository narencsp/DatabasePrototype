package presentation;

import main.Main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class UserCreation {
    private String userName, password, rPassword;

    public enum UserCreateFlag{
        USER_PASSWORDMATCH,
        USER_PASSWORDMISMATCH,
        USER_NULL
    }
    UserCreateFlag ucf;

    public void createUser() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Username");
        userName = in.nextLine();
        System.out.println("Enter Password");
        password = in.nextLine();
        System.out.println("Repeat Password");
        rPassword = in.nextLine();
        if(checkUserAlreadyExists(userName)){
            System.out.println("User already exists");
        }
        else{
            ucf=ValidateDetails(userName,password);
            switch(ucf){
                case USER_NULL:
                    System.out.println("Enter credentials");
                    break;
                case USER_PASSWORDMATCH:
                    System.out.println("User creation successful");
                    break;
                case USER_PASSWORDMISMATCH:
                    System.out.println("Password didn't match, try again!");
                    break;
            }
        }
    }

    private Boolean checkUserAlreadyExists(String userName){
        String line = "";
        String splitBy = ",";

        try
        {

            BufferedReader br = new BufferedReader(new FileReader("src/com/package/presentation/users.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] users = line.split(splitBy);
                if(users[0].equals(userName)){
                    return  true;
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private UserCreateFlag ValidateDetails(String userName, String password) throws IOException {
        Boolean result=false;
        UserCreation.UserCreateFlag ret = null;
        if(userName.isEmpty() || password.isEmpty()){
            ret = ucf.USER_NULL;
        }
        else if(password.equals(rPassword)){
            password = encryptPassword(password);
            createUser(userName, password);
            ret =  ucf.USER_PASSWORDMATCH;

        }
        else{
            ret = ucf.USER_PASSWORDMISMATCH;
        }
        return ret;

    }

    private static String encryptPassword(String nonEncryptedPassword){
        return Base64.getEncoder().encodeToString(nonEncryptedPassword.getBytes(StandardCharsets.UTF_8));
    }

    private void createUser(String userName, String password) throws IOException {

        PrintWriter pw = null;
        FileWriter fw = null;

        fw = new FileWriter("src/com/package/presentation/users.csv", true);

        fw.append("\n");
        fw.append(userName + ",");
        fw.append(password);
        fw.flush();
        fw.close();

    }

}
