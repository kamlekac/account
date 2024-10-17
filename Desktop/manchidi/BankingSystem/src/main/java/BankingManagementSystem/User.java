package BankingManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Connection connection;
    private Scanner scanner;
   // private Accounts accounts;

    public User(Connection connection, Scanner scanner){
        this.connection= connection;
        this.scanner=scanner;
    }
    // to register a user
    public void register(){
        scanner.nextLine();
        System.out.println("Enter full_name: ");
        String full_name= scanner.nextLine();
        System.out.println("Enter email: ");
        String email= scanner.nextLine();
        System.out.println("Enter password: ");
        String password= scanner.nextLine();
        // here checking if user is registered and not created account, he just have to create account if registered or has to be registered first to create account

        if(user_exists(email)){
            System.out.println("User is already exists for this email address");
            return;
        }
        String query= "Insert into User(full_name, email, password) Values(?,?,?)";
        try{
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, full_name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            int affectedRows= preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Registration successful");
            }else{
                System.out.println("Registration is not successful");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String login(){
        scanner.nextLine();
        System.out.println("Enter email");
        String email= scanner.nextLine();
        System.out.println("Enter password");
        String password= scanner.nextLine();

        String login_query="Select * from user where email=? and password=?";
        try{
            PreparedStatement preparedStatement= connection.prepareStatement(login_query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                return email; // using this email in bankingapp class to login email= user.login()
               // return resultSet.getString(login());
            }else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean user_exists(String email){
        String query= "Select * from user where email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
