import java.io.File; //import File io
import java.io.FileNotFoundException; //import try catch error handling
import java.util.Scanner; //import Scanner object

public class Login{ //Main public class
    public static void main(String []args){ //What is run when the program is started
        Scanner user_input = new Scanner(System.in); //Create a new scanner
        String entered_username = Get_Credentials.get_username(user_input); //Runs get_username method and stores answer in varaible entered_username
        String entered_password = Get_Credentials.get_password(user_input); //Runs get_password method and stores answer in varaible entered_password
        user_input.close(); //closes user input Scanner
        User[] all_users = File_Scanner.create_users(); //
        System.out.println(all_users[2].encrypted_password);
    }
}

class Get_Credentials{
    public static String get_username(Scanner user_input){ //Gathers username using scanner made in main method
        System.out.println("Username: "); //Prompts user for username
        String entered_username = user_input.next(); //stores answer in variable entered_username
        return entered_username; //returns entered_username to main method
    }
    
    public static String get_password(Scanner user_input){ //Gathers password using scanner made in main method
        System.out.println("Password: "); //Prompts user for username
        String entered_password = user_input.next(); //stores answer in variable entered_password
        return entered_password; //returns entered_password to main method
    }
}

class File_Scanner{ //creates File_Scanner class
    public static User[] create_users(){ //Creates create_users method that expects and output of an array full of User objects
        User users[] = new User[6]; //Creates users array of User objects and iniates it with enough memory for 6 users.
        int index_counter = 0; //Creates and initiates a int variable named index_counter.  Used to count which user number the program is on.
        
        try { //starts a try block to make sure the file is there, and there are no errors
            File credentials_file = new File("credentials.txt"); //Makes a new File object named credentials_file and sets the file path to credentials.txt
            String pattern = "[^\"\\s]+|\"(\\\\.|[^\\\\\"])*\""; //Makes a String variable named pattern.  This allows will allow our scanner to reutn passwords in quotes to be returned as one string.
            Scanner file_reader = new Scanner(credentials_file); //Makes a new Scanner named file_reader and tells it to read File object named credentials_file 
            
            while (file_reader.hasNextLine()) { //performs the following block of code if there is another Line to be read
                users[index_counter] = new User(); //Creates a new User object in the users array
                users[index_counter].username = file_reader.findInLine(pattern); //Sets the new User's username to the next string that matches the pattern variable
                users[index_counter].encrypted_password = file_reader.findInLine(pattern); //Sets the new User's encrypted_password to the next string that matches the pattern variable
                users[index_counter].password = file_reader.findInLine(pattern); //Sets the new User's password to the next string that matches the pattern variable
                users[index_counter].role = file_reader.findInLine(pattern); //Sets the new User's role to the next string that matches the pattern variable
                file_reader.nextLine(); //Tells the Scanner to move on to the next Line
                index_counter++; //increments the index_counter up 1 to allow for a new User object to be made.
            }
            
            file_reader.close(); //Closes the file_reader Scanner
        }
        catch (Exception e) { //Runs this block of code if there is an error with gathering the file.
          System.out.println(e.getClass()); //prints out the Class of the error that occured.
        }
        return users; //returns the users array to the main method in Login.
    }
}

class User { //defines the Object user.
    String username; //Allows User objects to have a username property.
    String password; //Allows User objects to have a password property.
    String encrypted_password; //Allows User objects to have a encrypted_password property.
    String role; //Allows User objects to have a role property.
}
