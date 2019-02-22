import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.security.MessageDigest;

public class Authentication{
    public static User temporaryUser = new User();
    public static File adminFile = new File("admin.txt");
    public static File veterinarianFile = new File("veterinarian.txt");
    public static File zookeeperFile = new File("zookeeper.txt");
    public static int attemptCounter = 0;
    public static boolean successfulLogin = false;
    public static Scanner userInput = new Scanner(System.in);
    public static boolean logout = false;
    
    public static void main(String []args) throws Exception{
        while (!logout) {
            startLogin();
            if (successfulLogin) {
                promptLogout();
            }
        }
    }
    
    public static void startLogin() throws Exception {
        User[] allUsers = createUsers();
        attemptCounter = 0;
        successfulLogin = false;
        
        while (attemptCounter < 3  && !successfulLogin) {
            getTemporaryUserCredentials(userInput);
            for (User u : allUsers) {
                if (temporaryUser.username.equals(u.username)) {
                    if (temporaryUser.encryptedPassword.equals(u.encryptedPassword)) {
                        printFile(u.role);
                        successfulLogin = true;
                        break;
                    }
                }
            }
            attemptCounter++;
        }
        
        if (attemptCounter == 3 && !successfulLogin) {
            userInput.close();
            logout = true; 
            System.out.println(); //prints out a blank line for easier readability
            System.out.println("You have made too many unsuccessful attempts.  The program will now exit.");
        }
    }
    
    public static void promptLogout(){
        System.out.println(); //prints out a blank line for easier readability
        System.out.println("Would you like to log out?  Please type \"y\" for Yes or \"n\" for No.");
        if ("y".equals(userInput.next())) {
            logout = true;
        }
        userInput.nextLine();
    }
    
    public static void getTemporaryUserCredentials(Scanner userInput) throws Exception{
        System.out.print("Username: ");
        temporaryUser.username = userInput.nextLine();
        System.out.print("Password: ");
        temporaryUser.encryptedPassword = encrypt(userInput.nextLine());
    }
    
    public static String encrypt(String original) throws Exception {
        StringBuffer sb = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
	}
    
    public static User[] createUsers() throws Exception{
        User users[] = new User[6];
        int indexCounter = 0;
        File credentialsFile = new File("credentials.txt");
        String pattern = "[^\"\\s]+|\"(\\\\.|[^\\\\\"])*\"";
        Scanner fileReader = new Scanner(credentialsFile);

        while (fileReader.hasNextLine()) {
            users[indexCounter] = new User();
            users[indexCounter].username = fileReader.findInLine(pattern);
            users[indexCounter].encryptedPassword = fileReader.findInLine(pattern);
            users[indexCounter].password = fileReader.findInLine(pattern);
            users[indexCounter].role = fileReader.findInLine(pattern);
            if (fileReader.hasNextLine() == true) {
                fileReader.nextLine();
            }
            indexCounter++;
        }
        fileReader.close();
        return users;
    }
    
    public static void printFile(String role) throws Exception {
        System.out.println(); //prints a blank line for easier readability.
        if (role.equals("admin")) {
            Scanner fileReader = new Scanner(adminFile);
            while (fileReader.hasNextLine()) {
                System.out.println(fileReader.nextLine());
            }
        }
        else if (role.equals("veterinarian")) {
            Scanner fileReader = new Scanner(veterinarianFile);
            while (fileReader.hasNextLine()) {
                System.out.println(fileReader.nextLine());
            }
        }
        else {
            Scanner fileReader = new Scanner(zookeeperFile);
            while (fileReader.hasNextLine()) {
                System.out.println(fileReader.nextLine());
            }
        }
    }
}

class User {
    String username;
    String password;
    String encryptedPassword;
    String role;
}
