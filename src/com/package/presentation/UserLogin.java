package presentation;

import java.io.*;

import java.util.Base64;
import java.util.Scanner;

public class UserLogin {


    private String userName, password;
    public String recordName, tableName, recordValue;

    public enum UserLoginFlag{
        USER_VALID,
        USER_WRONGCREDENTIALS,
        USER_NULL
    }
    public UserLoginFlag ulf;
    public void getUsername() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Username");
        userName = in.nextLine();
        System.out.println("Enter Password");
        password = in.nextLine();
        ulf=ValidateUser(userName,password);
        switch(ulf){
            case USER_NULL:
                System.out.println("Enter credentials");
                break;
            case USER_VALID:
                System.out.println("Credentials are valid");
                break;
            case USER_WRONGCREDENTIALS:
                System.out.println("Username/password is incorrect, try again!");
                break;
        }
    }

     protected UserLoginFlag ValidateUser(String userName, String password) {
        Boolean result=false;
        UserLoginFlag ret = null;
        if(userName.isEmpty() || password.isEmpty()){
            ret = ulf.USER_NULL;
        }
        else{

             result = AuthenticateUser(userName, password);
             if(result.equals(true)){
                 ret =  ulf.USER_VALID;
             }
             else{
                 ret = ulf.USER_WRONGCREDENTIALS;
             }
        }
        return ret;
    }

    protected Boolean AuthenticateUser(String userName, String password)  {
        String line = "";
        String splitBy = "~";

        try
        {

            BufferedReader br = new BufferedReader(new FileReader("src/com/package/tables/users.txt"));
            while ((line = br.readLine()) != null)
            {
                if(line.equals("#USER")){
                    recordName = line;
                }
                else if(line.equals("@user")){
                    recordValue = line;
                }
                else{
                    String[] users = line.split(splitBy);
                    users[1] =decryptPassword(users[1]);
                    if(users[0].equals(userName) && users[1].equals(password)){
                        return true;
                    }
                }

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private static String decryptPassword(String encryptedPassword){
        return new String(Base64.getMimeDecoder().decode(encryptedPassword));
    }

}
