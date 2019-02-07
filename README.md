# Southern New Hampshire University IT 145 - Final Project
<p>In this project, I choose the first option of making a Zoo Authentication System.  Please see <a href="/instructions.pdf">Project Guidelines</a> for the full instructions and Rubric.</p>

## Process Documentation
### Problem Statement / Scenario:
<p>The program I plan to develop, will initaite by gathering a user input for their username and password.  I will then convert the password to a hash using MD5.  After converted, I will compare the hash with the stored hash in the credentials file.  Each time the user enters a username and password, I will have a counter that increases by 1.  If there has been 3 failed attempts, I will notify the user and then exit the program.  If the user correctly enters their username and password, they will gain access to the system.  The system information stored in the role will be displayed and it will also allow the user to log out.  It will then return to the log in page to start over again.</p>

### Overall Process:
<p>I started off by making sudo code for the program, which can be found below.  I then used that code as a guideline to make my finished project.</p>

### Pseudocode:
  ```
  attempt_counter = 0
  
  function gather_username_and_password():
    entered_username = input("Username:")
    entered_password = input("Password:")
    attempt_counter = attempt_counter + 1
    
  fuction encyrpt_password(entered_password):
    encypted_password = md5_encrypt(entered_password)
    
  function check_credentials(entered_username):
    correct_username = check_username()
    if correct_username:
      correct_password = check_password()
        if correct_password:
          allow_access()
    else:
      if attempt_counter < 3:
        gather_username_and_password()
      else:
        print("You have entered incorrect credentials three times.  This program is now exiting.")
        system.exit()
    
      
  function check_username():
    for username in credentials_file:
      if entered_username == current_username:
        return true
      else:
        next username
    return false
  
  function check_password(encrypted_password):
    return encypted_password == correct_password
  
  function allow_access():
    open role_file.txt
    read role_file.txt
    close role_file.txt
    ask_logout()
  
  function ask_logout():
    logout = input("Type /"L/" when you are ready to log off:")
    if logout == "L":
      logout()
      
    
  function logout():
    system.clear_screen()
    start_login()
  
  function start_login()
    gather_username_and_password()
    encyprt_password(entered_password)
    check_credentials(entered_username, encrypted_password)
    
  start_login()  
  ```
