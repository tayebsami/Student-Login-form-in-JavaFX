package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import DBConnection.DatabaseConnection;

import java.io.IOException;
import java.sql.*;

public class Controller {
@FXML private TextField username;
@FXML private PasswordField password;
@FXML private ImageView image;
@FXML private Button Login;
@FXML private Button signup;
@FXML private RadioButton male;
@FXML private ToggleGroup gender;
@FXML private RadioButton female;
@FXML private PasswordField btnPassword;
@FXML private TextField btnUser;
@FXML private Button SignUpButton;
@FXML private PasswordField btnPasswordConfirm;
@FXML private Label passwordConfirmLabel;
@FXML private Label registrationMessageLabel;
@FXML private Label logintTryLabel;

    public void ChangeSceneSignUp(ActionEvent event) throws IOException {
        Parent signupRoot = FXMLLoader.load(getClass().getResource("SingUp.fxml"));
        Scene signupScene = new Scene(signupRoot);
        //this line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signupScene);
        window.show();
    }
    public String getGender(){
        String gen="";
        if (male.isSelected())
            gen="Male";
        else if (female.isSelected())
            gen="Female";

        return gen;
    }
    public void SignUpButton(ActionEvent eventSignUp){
        DatabaseConnection DBSignUp = new DatabaseConnection();
        Connection connectDB = DBSignUp.getConnection();
        String connectQuery = "INSERT INTO student(username,password,gender)" + "VALUES(?,?,?)";
        try {
           Statement statement = connectDB.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void RetrunSceneLogin(ActionEvent eventReturn) throws IOException {
        Parent ReturnLoginRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene RetrunLoginScene = new Scene(ReturnLoginRoot);

        //this line gets the stage information
        Stage window = (Stage) ((Node)eventReturn.getSource()).getScene().getWindow();
        window.setScene(RetrunLoginScene);
        window.show();

    }
    public void registrButton(ActionEvent e){
        passwordVerification();
    }
    public void passwordVerification(){
            if (btnPassword.getText().equals(btnPasswordConfirm.getText())) {
                registerUser();
                passwordConfirmLabel.setText((""));
            } else
                passwordConfirmLabel.setText("Password does not match !");
    }
    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String username = btnUser.getText();
        String password = btnPassword.getText();

        String insert = "INSERT INTO student(username,password,gender) VALUES ('";
        String insertValues = username + "','" + password + "','" + getGender() + "')";
        String insertToRegister = insert + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registrationMessageLabel.setText("User has been registred seccessfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loginButton(ActionEvent e){
        if(username.getText().isEmpty() == false && password.getText().isEmpty() == false){
            validateLogin();
        }else{
            logintTryLabel.setText("Please enter your Username and Password!");
        }
    }
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(*) FROM student WHERE username ='" + username.getText() +"'AND password ='" + password.getText()+"'";
        try {
           Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                int i = 1;
                if (queryResult.getInt(i) == 1) {
                    logintTryLabel.setText("Congratulation!");
                }else{
                    logintTryLabel.setText("Invalide login. Please Try Again!");
                }
               queryResult.getInt(i++);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
