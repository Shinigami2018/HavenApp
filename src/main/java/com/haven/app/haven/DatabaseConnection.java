package com.haven.app.haven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
//ariful
public class DatabaseConnection {
    public Connection databaseLink;

   public static void signupuser (ActionEvent event,String Email,String Username,String Password,String Repassword) throws SQLException {
    Connection connection = null;
    PreparedStatement psInsert = null;
    PreparedStatement psCheckUserExists = null;
    ResultSet resultSet = null;
    try {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "124519@#maisk#");
        psCheckUserExists = connection.prepareStatement("select * from useraccounts where username='" + Username + "'");
        resultSet = psCheckUserExists.executeQuery();
        if (resultSet.isBeforeFirst()) {
            System.out.println("Username already exists");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.showAndWait();
        }
        else
        {
            psInsert=connection.prepareStatement("INSERT INTO useraccounts(username,password,repassword,email) VALUES(?,?,?,?) ");
            psInsert.setString(1,Username);
            psInsert.setString(2,Password);
            psInsert.setString(3,Repassword);
            psInsert.setString(4,Email);
            psInsert.executeUpdate();
            signupuser(event,Email,Username,Password,Repassword);

        }
    }
    catch (SQLException e) {
        e.printStackTrace();

    }
    finally {
        if(resultSet!=null)
        { try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }

        }
        if(psInsert!=null)
        {
            try { psInsert.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        if(psCheckUserExists != null)
        {
            try { psCheckUserExists.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        if(connection!=null)
        {
            try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

}

public static void loginuser(ActionEvent event,String Email,String Username,String Password,String Repassword) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "124519@#maisk#");
            preparedStatement=connection.prepareStatement("select * from useraccounts where username='" + Username + "'");
            resultSet=preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst())
            {
                System.out.println("Username already exists");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.showAndWait();

            }else
            {
                while(resultSet.next())
                {
                    String gusername = resultSet.getString("username");
                    String gpassword = resultSet.getString("password");
                    String grepassword = resultSet.getString("repassword");
                    String gemail = resultSet.getString("email");
                    if(gpassword.equals(Password) && gusername == Username)
                    {
                        loginuser(event,Email,Username,Password,Repassword);
                    }
                    else
                    {
                        System.out.println("Wrong Password");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.showAndWait();
                        

                    }

                }
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(resultSet!=null)
                { try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }}
            if(preparedStatement!=null)
                { try { preparedStatement.close(); } catch (SQLException e) { e.printStackTrace(); }}
            if(connection!=null)
            {
                try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
                
            }
        }

}

}
