package com.example;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WelcomePageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField singUpUsername;
    @FXML
    private PasswordField singUpPassword;
    @FXML
    private TextField singUpEMail;
    @FXML
    private TextField logInUsername;
    @FXML
    private PasswordField logInPassword;

    public void logIn(MouseEvent event) throws Exception {
        String username = logInUsername.getText();
        String password = logInPassword.getText();
        User user = User.logIn(username, password);
        if (user != null) {
            User.setCurrentUser(user);
            goToMenuPage(event);
        }
    }


    public void goToSingUpPage(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("singUpPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToLogInPage(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("logInPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToMenuPage(MouseEvent event) throws Exception {
        if (User.getCurrentUser() != null) {
            String accountType = User.getCurrentUser().getAccountType();
            String fxmlFile = accountType.equals("Student") ? "studentMenuPage.fxml" : "teacherMenuPage.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}

