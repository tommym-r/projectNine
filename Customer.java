import java.util.*;
import java.io.*;

public class Customer extends User {
  private CheckingAccount checking;
  private SavingsAccount savings;

  public static void main(String args []){
    Customer c = new Customer();
    c.setUserName("Alice");
    c.setPIN("1111");
    c.start();
  } // end main

  public Customer(){
    checking = new CheckingAccount();
    savings = new SavingsAccount();
  } // end constructor

  public String menu(){
    Scanner input = new Scanner(System.in);
    System.out.println();
    System.out.println("0) exit");
    System.out.println("1) Manage Checking Account");
    System.out.println("2) Manage Savings Account");
    System.out.println("3) Change PIN");
    System.out.println();
    System.out.println("Please enter 0-3");
    String response = input.nextLine();
    return response;
  } // end menu

  public void start(){
    boolean keepGoing = true;
    String response;
    boolean loggedIn = false;
    while (loggedIn == false){
      loggedIn = login();
      if (loggedIn == false){
        System.out.println("Incorrect username or password, try again");
      } // end if
    } // end while
    System.out.println("Login Successful");

    while (keepGoing){
      response = menu();
      if (response.equals("0")){
        keepGoing = false;
      } else if (response.equals("1")){
        checking.start();
      } else if (response.equals("2")){
        savings.start();
      } else if (response.equals("3")){
        this.changePIN();
      } else {
        System.out.println("Please enter 0, 1, 2, or 3");
      } // end if
    } // end while
  } // end start

  public void changePIN(){
    boolean keepGoing = true;
    Scanner input = new Scanner(System.in);
    String response;
    String tempPIN;
    
    while (keepGoing){
      System.out.println("Enter your current PIN: ");
      response = input.nextLine();
      if (response.equals(this.getPIN())){
        System.out.println();
        System.out.println("Enter your new PIN: ");
        tempPIN = input.nextLine();
        System.out.println();
        System.out.println("Re-enter your new PIN: ");
        response = input.nextLine();
        if (response.equals(tempPIN)){
          this.setPIN(tempPIN);
          System.out.println("PIN changed successfully");
          keepGoing = false;
        } else {
          System.out.println("Your PINs did not match, please try again");
        } // end if
      } else {
        System.out.println("Incorrect PIN, please try again");
      } // end if
    } // end while
  } // end changePIN
  

  public String getReport(){
    String report = "User Report:\n" +
                    "Checking balance: " + checking.getBalanceString() + "\n" +
                    "Savings balance: " + savings.getBalanceString();
    return report;
  } // end getReport

} // end Customer
