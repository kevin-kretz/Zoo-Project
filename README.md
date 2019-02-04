# Southern New Hampshire University IT 145 - Final Project
<p>In this project, I choose the first option of making a Zoo Authentication System.  Please see <a href="/it_145_final_porject.pdf">Project Guidelines</a> for the full instructions and Rubric.</p>

## Process Documentation
### Problem Statement / Scenario:
<p>The program I plan to develop, will initaite by gathering a user input for their username and password.  I will then convert the password to a hash using MD5.  After converted, I will compare the hash with the stored hash in the credentials file.  Each time the user enters a username and password, I will have a counter that increases by 1.  If there has been 3 failed attempts, I will notify the user and then exit the program.  If the user correctly enters their username and password, they will gain access to the system.  The system information stored in the role will be displayed and it will also allow the user to log out.  It will then return to the log in page to start over again.</p>

### Overall Process:
<p>I started off by making sudo code for the program, which can be found below.  I then used that code as a guideline to make my finished project.</p>

### Pseudocode:
  '''
  function gather_information():
    entered_username = input("Username:")
    entered_password = input("Password:")
    
  fuction md5(entered_password):
    return encrypted_password
  for each_password in credentials_file:
    if encrypted_pass
  '''
