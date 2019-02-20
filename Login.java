import java.util.Scanner;

public class Login{
    public static void main(String []args){ //What is run when the program is started
        Scanner user_input = new Scanner(System.in); //Create a new scanner
        String entered_username = Get_Credentials.get_username(user_input); //Runs get_username method and stores answer in varaible entered_username
        String entered_password = Get_Credentials.get_password(user_input); //Runs get_password method and stores answer in varaible entered_password
        System.out.println(entered_username); //Prints out entered_username for testing purposes.  Will be removed later.
        System.out.println(entered_password); //Prints out entered_password for testing purposes.  Will be removed later.
    }
}

class Get_Credentials{
    public static String get_username(Scanner user_input){ //Gathers username using scanner made in main method
        System.out.println("Username: "); //Prompts user for username
        String entered_username = user_input.next(); //stores answer in variable entered_username
        return entered_username; //returns entered_username to main method
    }
    
    public static String get_password(Scanner user_input){ //Gathers password using scanner made in main method
        System.out.println("Password: ");  //Prompts user for username
        String entered_password = user_input.next(); //stores answer in variable entered_password
        return entered_password; //returns entered_password to main method
    }
}
