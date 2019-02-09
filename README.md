# Southern New Hampshire University IT 145 - Final Project
<p>In this project, I choose the first option of making a Zoo Authentication System.  Please see <a href="/Instructions.pdf">Project Guidelines</a> for the full instructions and Rubric.</p>

## Process Documentation
### Problem Statement / Scenario:
<p>The program I plan to develop, will initaite by gathering a user input for their username and password.  I will then convert the password to a hash using MD5.  After converted, I will compare the hash with the stored hash in the credentials file.  Each time the user enters a username and password, I will have a counter that increases by 1.  If there has been 3 failed attempts, I will notify the user and then exit the program.  If the user correctly enters their username and password, they will gain access to the system.  The system information stored in the role will be displayed and it will also allow the user to log out.  It will then return to the log in page to start over again.</p>

### Overall Process:
<p>I started off by making sudo code for the program, which can be found below.  I then used that code as a guideline to make my finished project.</p>

### Pseudocode:
  ```
  SET attempt_counter = 0 
  CALL start_login()
  
  function start_login()
    SET entered_username = CALL get_username()
    SET entered_password = CALL get_password()
    INCREMENT attempt_counter
    SET enrypted_password = CALL encyprt_password(entered_password)
    CALL check_credentials(entered_username, encrypted_password)

  function gather_username()
    PRINT "Username:"
    OBTAIN username
    RETURN username
    
  function gather_password()
    PRINT "Password:"
    OBTAIN password
    RETURN password
  
  fuction encyrpt_password(entered_password)
    CALCULATE encrypted_password using MD5
    RETURN encrypted_password

  function check_credentials(entered_username, encrypted_password)
    SET correct_username = check_username()
    IF correct_username
      SET correct_password = check_password()
      IF correct_password
        CALL allow_access()
      END IF
    ELSE
      IF attempt_counter < 3
        CALL gather_username_and_password()
      ELSE
        PRINT "You have entered incorrect credentials three times.  This program is now exiting."
        EXIT
      END IF
    END IF
  
  function check_username():
    FOR each username in credentials_file
      IF entered_username == current_username
        RETURN true
      ELSE:
        go to next username in credentials file
      END IF
    END FOR
    RETURN false
  
  function check_password(encrypted_password):
    RETURN encypted_password == correct_password
  
  function allow_access():
    OBTAIN user's role file
    OPEN user's role file
    DIPSLAY user's role file
    CLOSE user's role file
    CALL ask_logout()
  
  function ask_logout():
    PRINT "Would you like to log out?"
    OBTAIN user's log out choice
    IF user's logout choice == true
      CALL logout()
    END IF
      
  function logout():
    clear computer screen
    CALL start_login()
  ```
