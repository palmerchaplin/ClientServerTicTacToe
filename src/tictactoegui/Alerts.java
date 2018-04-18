
package tictactoegui;

import javafx.scene.control.Alert;

public class Alerts {
    public static void logoutMessage() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Logout Alert");
        alert.setHeaderText("You have been logged out.");
        alert.showAndWait();
    }
    public static void invalidUsernameMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Username Alert");
        alert.setHeaderText("Username must be at least 2 characters.");
        alert.showAndWait();
    }
    public static void invalidPasswordMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Password Alert");
        alert.setHeaderText("Password must be at least 6 characters. \nPassword must contain lowercase, uppercase, \na number, and a special character.");
        alert.showAndWait();
    }
    public static void passwordsDontMatchMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Password Alert");
        alert.setHeaderText("passwords provided do not match.");
        alert.showAndWait();
    }
    public static void invalidEmailMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Email Alert");
        alert.setHeaderText("Email must be of valid type. \n(e.g. account@domainname.typ)");
        alert.showAndWait();
    }
    public static void failedLoginMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Alert");
        alert.setHeaderText("Incorrect Username or Password");
        alert.showAndWait();
    }
    public static void lockOutMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lock Out");
        alert.setHeaderText("This account is locked. \nPlease go to recover password");
        alert.showAndWait();
    }
    public static void invalidConnectionAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Connection");
        alert.setHeaderText("The entered IP is not valid");
        alert.showAndWait();
    }
    public static void disconnectedAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("disconnected");
        alert.setHeaderText("You have been Disconnected");
        alert.showAndWait();
    }
    public static void userStatAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Stats");
        alert.setHeaderText("Here are some user stats");
        alert.showAndWait();
    }
    public static void serverStatAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Server Stats");
        alert.setHeaderText("Here are some server stats");
        alert.showAndWait();
    }
    
    public static void NACWinAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Naughts and crosses");
        alert.setHeaderText("You have won!");
        alert.showAndWait();
    }
    public static void NACLooseAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Naughts and crosses");
        alert.setHeaderText("You have lost!");
        alert.showAndWait();
    }
    public static void NACTieAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Naughts and crosses");
        alert.setHeaderText("You have Tied!");
        alert.showAndWait();
    }
}
