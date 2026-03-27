import java.util.*;
import java.io.*;

public class CheckingAccount implements HasMenu, Serializable{
  double balance;

  public static void main(String[] args){
    CheckingAccount ca = new CheckingAccount();
    ca.start();
  } // end main

  public CheckingAccount(){
    this.balance = 0d;
  } // end constructor
  
  public CheckingAccount(double balance){
    this.balance = balance;
  } // end constructor

  public String menu(){
    Scanner input = new Scanner(System.in);
    System.out.println();
    System.out.println("0) quit");
    System.out.println("1) check balance");
    System.out.println("2) make deposit");
    System.out.println("3) make a withdrawal");
    System.out.println();
    System.out.print("please enter 0-3: ");
    String response = input.nextLine();
    return response;
  } // end menu

  public void start(){
    boolean keepGoing = true;
    String response;
    while (keepGoing){
      response = menu();
      if (response.equals("0")){
        keepGoing = false;
      } else if (response.equals("1")){
        System.out.println(" Checking balance... ");
        this.checkBalance();
      } else if (response.equals("2")){
        System.out.println(" Making a deposit... ");
        this.makeDeposit();
      } else if (response.equals("3")){
        System.out.println(" Making a withdrawal... ");
        this.makeWithdrawal();
      } else {
        System.out.println(" Please enter 0, 1, 2, or 3");
      } // end if
    } // end while
  } // end start

  public double getBalance(){
    return this.balance;
  } // end getBalance

  public String getBalanceString(){
    String result = String.format("$%.02f", this.balance);
    return result;
  } // end getBalanceString

  public void setBalance(double balance){
    this.balance = balance;
  } // end setBalance

  public void checkBalance(){
    System.out.print("Current balance: ");
    System.out.println(this.getBalanceString());
  } // end checkBalance

  private double getDouble(){
    Scanner input = new Scanner(System.in);
    String resultString = input.nextLine();
    double result = 0d;
    try {
      result = Double.parseDouble(resultString);
    } catch (Exception e){
      System.out.println("Not a legal input. Changing to 0");
      result = 0d;
    } // end try
    if (result < 0){
      System.out.println("Not a legal input, please enter a positive number. Changing to 0");
      result = 0d;
    } // end if
    return result;
  } // end getDouble

  public void makeDeposit(){
    System.out.print("How much to deposit: ");
    double deposit = this.getDouble();
    this.balance += deposit;
    System.out.println("New balance: " + this.getBalanceString());
  } // end makeDeposit

  public void makeWithdrawal(){
    System.out.print("How much to withdrawal: ");
    double withdrawal = this.getDouble();
    if (withdrawal > this.balance){
      System.out.println("Insufficient funds");
    } else {
      this.balance -= withdrawal;
    } // end if
    System.out.println("New balance: " + this.getBalanceString());
  } // end makeWithdrawal
} // end class CheckingAccount

