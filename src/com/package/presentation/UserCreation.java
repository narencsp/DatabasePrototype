package presentation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class UserCreation {
    private String userName, password, rPassword;
    public String recordName, recordValue;

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
            ucf=ValidateDetails(userName,password,rPassword);
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

    protected Boolean checkUserAlreadyExists(String userName){
        String line = "";
        String splitBy = "~";

        try
        {

            BufferedReader br = new BufferedReader(new FileReader("src/com/package/TABLES/users.txt"));
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
                    if(users[0].equals(userName)){
                        return  true;
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

    protected UserCreateFlag ValidateDetails(String userName, String password, String rPassword) throws IOException {

        UserCreation.UserCreateFlag ret = null;
        if(userName.isEmpty() || password.isEmpty()){
            enterUserLog(userName,"USER_NULL");
            ret = ucf.USER_NULL;
        }
        else if(password.equals(rPassword)){
            password = encryptPassword(password);
            createUser(userName, password);
            enterUserLog(userName,"USER_PASSWORDMATCH");
            ret =  ucf.USER_PASSWORDMATCH;

        }
        else{
            enterUserLog(userName,"USER_PASSWORDMISMATCH");
            ret = ucf.USER_PASSWORDMISMATCH;
        }
        return ret;

    }

    private static String encryptPassword(String nonEncryptedPassword){
        return Base64.getEncoder().encodeToString(nonEncryptedPassword.getBytes(StandardCharsets.UTF_8));
    }

    private void createUser(String userName, String password) throws IOException {


        FileWriter fw = null;

        fw = new FileWriter("src/com/package/TABLES/users.txt", true);

        fw.append("\n");
        fw.append(userName + "~");
        fw.append(password);
        fw.flush();
        fw.close();

    }

    private void enterUserLog(String username, String response) {
        try {
            File file = new File("src/com/package/LOG/userlog.txt");
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                if (fileWriter != null) {
                    fileWriter.append(username+"\t"+response+"\t->REGISTRATION"+"\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
