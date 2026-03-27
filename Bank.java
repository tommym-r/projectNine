import java.util.*;
import java.io.*;

public class Bank implements HasMenu {
  Admin admin = new Admin();
  CustomerList customers = new CustomerList();

  public static void main(String[] args){
    new Bank();
  } // end main

  public Bank() {
    //this.loadSampleCustomers();
    //this.saveCustomers();
    this.loadCustomers();
    this.start();
    this.saveCustomers();
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
    System.out.print("Action: ");
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
        System.out.println();
        System.out.println("Admin login");
        System.out.println();
        if (this.admin.login()){
          startAdmin();
        } // end if
      } else if (response.equals("2")){
        System.out.println();
        System.out.println("Customer login");
        System.out.println();
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
        System.out.println("Add new customer");
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
    System.out.print("User name: ");
    String userName = input.nextLine();
    System.out.print("PIN: ");
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
    System.out.print("User name: ");
    String userNameIn = input.nextLine();
    System.out.print("PIN: ");
    String PINin = input.nextLine();

    Customer currentCustomer = null;
    for (Customer customer: customers){
      if (customer.login(userNameIn, PINin)){
        currentCustomer = customer;
      } // end if
    } // end for

    if (currentCustomer == null){
      System.out.println("Incorrect username or password");
    } else {
      System.out.println();
      System.out.println("Login Successful"); 
      // System.out.println();
      currentCustomer.start();
    } // end if
  } // end loginAsCustomer

  public void saveCustomers(){
    try {
      FileOutputStream fo = new FileOutputStream("customers.dat");
      ObjectOutputStream obOut = new ObjectOutputStream(fo);
      obOut.writeObject(customers);
      obOut.close();
      fo.close();
    } catch (Exception e){
      System.out.println(e.getMessage());
    } // end try
  } // end saveCustomers


  public void loadCustomers(){
    try {
      FileInputStream fi = new FileInputStream("customers.dat");
      ObjectInputStream obIn = new ObjectInputStream(fi);
      customers = (CustomerList)(obIn.readObject());
      obIn.close();
      fi.close();
    } catch (Exception e){
      System.out.println(e.getMessage());
    } // end try
  } // end loadCustomers

} // end bank

class CustomerList extends ArrayList<Customer> {};
