import java.util.*;
import java.io.*;

public class Bank implements HasMenu {
  Admin admin = new Admin();
  CustomerList customers = new CustomerList();

  public static void main(String[] args){
    new Bank();
  } // end main

  public Bank() {
    this.loadSampleCustomers();
    this.start();
  } // end constuctor

  public String menu(){
    Scanner input = new Scanner(System.in);
    System.out.println();
    System.out.println("Bank Menu");
    System.out.println();
    System.out.println("0) Exit system");
    System.out.println("1) Login as admin");
    System.out.println("2) Login as customer");
    System.out.println();
    System.out.println("Action: ");
    String response = input.nextLine();
    return response;
  } // end menu

  public void start(){
    boolean keepGoing = true;
    while (keepGoing){
      String response = menu();
      if (response.equals("0")){
        keepGoing = false;
      } else if (response.equals("1")){
        System.out.println("Admin login");
        if (this.admin.login()){
          startAdmin();
        } // end if
      } else if (response.equals("2")){
        System.out.println("Customer login");
        this.loginAsCustomer();
      } else {
        System.out.println("Please enter 0, 1, or 2");
      } // end if
    } // end while
  } // end start

  public void startAdmin(){
    boolean keepGoing = true;
    while (keepGoing){
      String response = admin.menu();
      if (response.equals("0")){
        keepGoing = false;
      } else if (response.equals("1")){
        System.out.println("Full Customer Report");
        this.reportAllCustomers();
      } else if (response.equals("2")){
        System.out.println("Add new user");
        this.addUser();
      } else if (response.equals("3")){
        System.out.println("Apply interest to savings");
        this.applyInterest();
      } else {
        System.out.println("Please enter 0, 1, 2, 3");
      } // end if
    } // end while
  } // end startAdmin

  public void loadSampleCustomers(){
    customers.add(new Customer("Alice" , "1111"));
    customers.add(new Customer("Bob" , "2222"));
    customers.add(new Customer("Cindy" , "3333"));
    customers.add(new Customer("Dave" , "4444"));
  } // end loadSampleCustomers
  
  public void reportAllCustomers(){
    for (Customer customer: customers){
      System.out.println(customer.getReport());
      System.out.println();
    } // end for
  } // end reportAllCustomers

  public void addUser(){
    Scanner input = new Scanner(System.in);
    System.out.println("User name: ");
    String userName = input.nextLine();
    System.out.println("PIN: ");
    String PIN = input.nextLine();
    customers.add(new Customer(userName, PIN));
  } // end addUser

  public void applyInterest(){
    for (Customer customer: customers){
      customer.getSavings().calcInterest();
    } // end for
  } // end applyInterest

  public void loginAsCustomer(){
    Scanner input = new Scanner(System.in);
    System.out.println("User name: ");
    String userNameIn = input.nextLine();
    System.out.println("PIN: ");
    String PINin = input.nextLine();

    Customer currentCustomer = null;
    for (Customer customer: customers){
      if (customer.login(userNameIn, PINin)){
        currentCustomer = customer;
      } // end if
    } // end for

    if (currentCustomer == null){
      System.out.println("Customer not found");
    } else { 
      currentCustomer.start();
    } // end if
  } // end loginAsCustomer


} // end bank

class CustomerList extends ArrayList<Customer> {};
