package BankingManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Accounts {
    private Connection connection;
    private Scanner scanner;

    public Accounts(Connection connection, Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }


// method to open account
    public long openAccount(String email) {
        if (!account_exist(email)) {
            String open_account_query = "Insert into accounts(account_number, full_name, " +
                    "email, balance, security_pin) Values(?,?,?,?,?)";
            scanner.nextLine();
            System.out.println("Enter your full name");
            String full_name = scanner.nextLine();
            System.out.println("Enter initial amount");
            double balance = scanner.nextDouble();// first only email enter hence not asking again
            scanner.nextLine();
            System.out.println("Enter security_pin");
            String security_pin = scanner.nextLine();
            try {
                long account_number = generateAccountNumber();
                PreparedStatement preparedStatement = connection.prepareStatement(open_account_query);
                preparedStatement.setLong(1, account_number);
                preparedStatement.setString(2, full_name);
                preparedStatement.setString(3, email);
                preparedStatement.setDouble(4, balance);
                preparedStatement.setString(5, security_pin);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return account_number;
                } else {
                    throw new RuntimeException("Account creation failed !");
                }
            } catch (SQLException e) {
                System.out.println(e.getStackTrace());
            }
        }
        throw new RuntimeException("Account already exists !");
    }

    // to get account number by passing email or to know
    public long getAccountNumber(String email){
        String query= "Select account_number from accounts where email=?";
        try{
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getLong("account_number");
            }
        }catch(SQLException e){
           e.printStackTrace();
        }
        throw new RuntimeException("Account number does not exist !");
    }
    private long generateAccountNumber(){
        try{
           Statement statement= connection.createStatement();
           ResultSet resultSet= statement.executeQuery("Select account_number from accounts Order By " +
                   "account_number DESC LIMIT 1");
           if(resultSet.next()){
               long last_account_number=resultSet.getLong("account_number");
               return last_account_number+1;
           } else{
               return 10000100;
           }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 10000100;
    }
    public boolean account_exist(String email) {
        String query= "Select account_number from accounts where email=?";
        try{
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
