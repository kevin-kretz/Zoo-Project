import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.security.MessageDigest;

public class Authentication{
    public static User temporary_user = new User();
    public static File admin_file = new File("admin.txt");
    public static File veterinarian_file = new File("veterinarian.txt");
    public static File zookeeper_file = new File("zookeeper.txt");
    public static int attempt_counter = 0;
    public static boolean successful_login = false;
    public static Scanner user_input = new Scanner(System.in);
    public static boolean log_out = false;
    
    
   
    public static void main(String []args) throws Exception{
        while (!log_out) {
            start_login();
            if (successful_login) {
                prompt_log_out();
            }
        }
    }
    
    public static void start_login() throws Exception {
        User[] all_users = create_users();
        attempt_counter = 0;
        successful_login = false;
        
        while (attempt_counter < 3  && !successful_login) {
            get_temporary_user_credentials(user_input);
            for (User u : all_users) {
                if (temporary_user.username.equals(u.username)) {
                    if (temporary_user.encrypted_password.equals(u.encrypted_password)) {
                        print_file(u.role);
                        successful_login = true;
                        break;
                    }
                }
            }
            attempt_counter++;
        }
        
        if (attempt_counter == 3 && !successful_login) {
            user_input.close();
            System.out.println(); //prints out a blank line for easier readability
            System.out.println("You have made too many unsuccessful attempts.  The program will now exit.");
        }
    }
    
    public static void prompt_log_out(){
        System.out.println(); //prints out a blank line for easier readability
        System.out.println("Would you like to log out?  Please type \"y\" for Yes or \"n\" for No.");
        if ("y".equals(user_input.next())) {
            log_out = true;
        }
    }
    
    public static void get_temporary_user_credentials(Scanner user_input) throws Exception{
        System.out.print("Username: ");
        temporary_user.username = user_input.nextLine();
        System.out.print("Password: ");
        temporary_user.encrypted_password = encrypt(user_input.nextLine());
    }
    
    public static void check_credentials() {
        
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
    
    public static User[] create_users() throws Exception{
        User users[] = new User[6];
        int index_counter = 0;
        File credentials_file = new File("credentials.txt");
        String pattern = "[^\"\\s]+|\"(\\\\.|[^\\\\\"])*\"";
        Scanner file_reader = new Scanner(credentials_file);

        while (file_reader.hasNextLine()) {
            users[index_counter] = new User();
            users[index_counter].username = file_reader.findInLine(pattern);
            users[index_counter].encrypted_password = file_reader.findInLine(pattern);
            users[index_counter].password = file_reader.findInLine(pattern);
            users[index_counter].role = file_reader.findInLine(pattern);
            file_reader.nextLine();
            index_counter++;
        }
        file_reader.close();
        return users;
    }
    
    public static void print_file(String role) throws Exception {
        System.out.println(); //prints a blank line for easier readability.
        if (role.equals("admin")) {
            Scanner file_reader = new Scanner(admin_file);
            while (file_reader.hasNextLine()) {
                System.out.println(file_reader.nextLine());
            }
        }
        else if (role.equals("veterinarian")) {
            Scanner file_reader = new Scanner(veterinarian_file);
            while (file_reader.hasNextLine()) {
                System.out.println(file_reader.nextLine());
            }
        }
        else {
            Scanner file_reader = new Scanner(zookeeper_file);
            while (file_reader.hasNextLine()) {
                System.out.println(file_reader.nextLine());
            }
        }
    }
}

class User {
    String username;
    String password;
    String encrypted_password;
    String role;
}
