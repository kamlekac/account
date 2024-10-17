package BankingManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    private Connection connection;
    private Scanner scanner;

    public AccountManager(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void credit_money(long account_number) throws SQLException {
        scanner.nextLine();
        System.out.println("Enter amount");
        double amount = scanner.nextDouble();
        System.out.println("Enter pin");
        String security_pin = scanner.next();
        scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            if (account_number != 0) {
                // this is to first find the account number by passing the pin, retrieving the account here
                String query = "Select * from accounts where account_number=? and security_pin=? ";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, account_number);
                preparedStatement.setString(2, security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // this is to credit in account, updating the account here
                    String credit_query = "Update accounts SET balance= balance + ? where account_number =?";
                    PreparedStatement credit_preparedStatement = connection.prepareStatement(credit_query);
                    credit_preparedStatement.setDouble(1, amount);
                    credit_preparedStatement.setLong(2, account_number);
                    int affected_rows = credit_preparedStatement.executeUpdate();
                    if (affected_rows > 0) {
                        System.out.println("The amount: " + amount + "is credited successfully");
                        // have to set the commit manually after the transaction
                        connection.commit();
                        connection.setAutoCommit(true);
                        return;
                    } else {
                        System.out.println("Transaction failed");
                        connection.rollback();
                        connection.setAutoCommit(true);
                    }
                } else {
                    System.out.println("Invalid security pin");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
        }

        public void debit_money(long account_number) throws SQLException{
        scanner.nextLine();
        System.out.println("Enter the amount: ");
        double amount=scanner.nextDouble();
        System.out.println("Enter the security_pin: ");
        String security_pin= scanner.next();
        scanner.nextLine();
        try{
            connection.setAutoCommit(false);
            if(account_number!=0){
           // you need to get the account where you are debiting, i.e. retrieve
            String query= "Select * from accounts where account_number=? and security_pin=?";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setLong(1, account_number);
            preparedStatement.setString(2, security_pin);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                double current_balance= resultSet.getDouble("balance");
                if(amount<=current_balance){
                    // giving the values and updating data
                    String debit_query="Update accounts SET balance= balance-? where account_number=?";
                    PreparedStatement debit_preparedstatement= connection.prepareStatement(debit_query);
                    debit_preparedstatement.setDouble(1, amount);
                    debit_preparedstatement.setLong(2, account_number);
                    int affected_rows= debit_preparedstatement.executeUpdate();
                    if(affected_rows>0){
                        System.out.println("Debited the "+amount+" successfully");
                        connection.commit();
                        connection.setAutoCommit(true);
                    }else{
                        System.out.println("Transaction fail");
                        connection.rollback();
                        connection.setAutoCommit(true);
                    }

                }else{
                    System.out.println("Insufficient balance");
                }

            }else{
                System.out.println("Invalid pin");
            }

        }
        } catch(SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }
    public void transfer_money(long sender_account_number) throws SQLException{
        scanner.nextLine();
        System.out.println("Enter receiver account number");
        long receiver_account_number= scanner.nextLong();
        System.out.println("Enter amount");
        double amount= scanner.nextDouble();
        System.out.println("Enter security_pin");
        String security_pin= scanner.next();
        scanner.nextLine();
            try{
              connection.setAutoCommit(false);
                if(sender_account_number!=0 && receiver_account_number!=0){
                  String query= "select * from accounts where account_number=? and security_pin=?";
                  PreparedStatement preparedStatement= connection.prepareStatement(query);
                  preparedStatement.setLong(1, sender_account_number);
                  preparedStatement.setString(2, security_pin);
                  ResultSet resultSet= preparedStatement.executeQuery();
                   if(resultSet.next()){
                      double current_balance= resultSet.getDouble("balance");

                        if(amount<=current_balance) {
                           String debit_query= "Update accounts SET balance= balance -? where account_number=?";
                           String credit_query="Update accounts SET balance= balance +? where account_number=?";

                           PreparedStatement debit_prepared= connection.prepareStatement(debit_query);
                           PreparedStatement credit_prepared= connection.prepareStatement(credit_query);

                           debit_prepared.setDouble(1, amount);
                           debit_prepared.setLong(2, sender_account_number);
                           int affected_rows1= debit_prepared.executeUpdate();



                           credit_prepared.setDouble(1, amount);
                           credit_prepared.setLong(2, receiver_account_number);
                           int affected_rows2= credit_prepared.executeUpdate();

                           if(affected_rows1>0 && affected_rows2>=0){
                               System.out.println("The amount"+ amount+ "Transferred successfully");
                               connection.commit();
                               connection.setAutoCommit(true);
                               return;

                           }else{
                               System.out.println(" Transaction failed");
                               connection.rollback();
                               connection.setAutoCommit(true);
                           }
                        }else{
                              System.out.println("Insufficient balance");
                          }
                      }else{
                          System.out.println("Invalid pin");
                      }
                   }else{
                       System.out.println("Invalid account");
                   }
            }catch(SQLException e){
                   e.printStackTrace();
               }
            connection.setAutoCommit(true);
    }

    public void getBalance(long account_number){
        scanner.nextLine();
        System.out.println("Enter security_pin");
        String security_pin= scanner.nextLine();

        try{

            String query= "Select balance from accounts where account_number=? and security_pin=?";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setLong(1, account_number);
            preparedStatement.setString(2, security_pin);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
              double balance= resultSet.getDouble("balance");
                System.out.println("balance: "+balance);
            }else{
                System.out.println("Invalid pin");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

   }

