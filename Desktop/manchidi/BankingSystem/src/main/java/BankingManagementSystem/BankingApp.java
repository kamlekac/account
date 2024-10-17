package BankingManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {
    // for connecting to db
    private static final String url = "jdbc:mysql://localhost:3306/BankingSystem";
    private static final String username = "root";
    private static final String password = "Chaitu@123";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try{
            Connection connection= DriverManager.getConnection(url, username, password );
            Scanner scanner = new Scanner(System.in);
            User user= new User(connection, scanner);
            Accounts accounts= new Accounts(connection, scanner);
            AccountManager accountManager= new AccountManager(connection, scanner);

            // this email and account_number are used everywhere in the other classe.
            String email;
            long account_number;

            while(true){
                System.out.println("Welcome to Banking System");
                System.out.println("Please select the option");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                int choice1= scanner.nextInt();
                switch (choice1){
                    case 1:
                       user.register();
                       break;
                    case 2:
                        email= user.login();
                        if(email!=null){
                            System.out.println();
                            System.out.println("User Logged In successfully");
                            if(!accounts.account_exist(email)){
                                System.out.println();
                                System.out.println("1. Open a new account");
                                System.out.println("2. Exit");
                                if(scanner.nextInt()==1){
                                   account_number=accounts.openAccount(email);
                                    System.out.println("Account created");
                                    System.out.println("Your account number is" +account_number);
                                }else{
                                    break;
                                }

                            }
                            account_number=accounts.getAccountNumber(email);
                            int choice2=0;
                            while(choice2!=5){
                                System.out.println();
                                System.out.println("1. Debit money");
                                System.out.println("2. Credit money");
                                System.out.println("3. Transfer money");
                                System.out.println("4. Check Balance");
                                System.out.println("5. Log out");
                                System.out.println("Enter choice");
                                choice2=scanner.nextInt();
                                switch(choice2){
                                    case 1:
                                        accountManager.debit_money(account_number);
                                        break;
                                    case 2:
                                        accountManager.credit_money(account_number);
                                        break;
                                    case 3:
                                        accountManager.transfer_money(account_number);
                                        break;
                                    case 4:
                                        accountManager.getBalance(account_number);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("Enter the valid option ");
                                        break;
                                }
                            }
                        }else{
                            System.out.println("Incorrect email or password");
                        }
                    case 3:
                        System.out.println("Thank you for using banking system");
                        System.out.println("Exiting system");
                        return;
                    default:
                        System.out.println("Enter valid option");
                        break;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}