import java.util.*;
import java.io.*;

public abstract class User implements HasMenu, Serializable{
  private String userName;
  private String PIN;

  public boolean login(){
    boolean success = false;
    Scanner input = new Scanner(System.in);
    System.out.print("User name: ");
    String userName = input.nextLine();
    System.out.println();
    System.out.print("PIN: ");
    String PIN = input.nextLine();
    if (userName.equals(this.userName)){
      if (PIN.equals(this.PIN)){
        success = true;
      } // end if
    } // end if
    return success;
  } // end login
  
  public boolean login(String userName, String PIN){
    boolean success = false;
    if (userName.equals(this.userName)){
      if (PIN.equals(this.PIN)){
        success = true;
      } // end if
    } // end if
    return success;
  } // end login

  public void setUserName(String userName){
    this.userName = userName;
  } // end setUserName

  public String getUserName(){
    return this.userName;
  } // end getUserName

  public void setPIN(String PIN){
    this.PIN = PIN;
  } // end setPIN

  public String getPIN(){
    return this.PIN;
  } // end getPIN

  public abstract String getReport();
} // end User
