package tictactoegui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;
import tictactoegui.Alerts;
import tictactoegui.Validation;

public class TicTacToeGUI extends Application {

    MenuItem login = new MenuItem("Login");
    MenuItem register = new MenuItem("Register");
    MenuItem changePassword = new MenuItem("Change Password");
    MenuItem recoverPassword = new MenuItem("Recover Password");
    MenuItem logout = new MenuItem("Logout");

    MenuItem newGame = new MenuItem("New Game");
    MenuItem userStats = new MenuItem("User Stats");

    MenuItem connect = new MenuItem("Connect");
    MenuItem disconnect = new MenuItem("Disconnect");
    MenuItem serverStats = new MenuItem("Server Stats");

    VBox root;
    MenuBar menuBar;
    Menu userMenu;
    Menu game;
    Menu server;
    GridPane grid;

    Pane s1 = new Pane();
    Pane s2 = new Pane();
    Pane s3 = new Pane();
    Pane s4 = new Pane();
    Pane s5 = new Pane();
    Pane s6 = new Pane();
    Pane s7 = new Pane();
    Pane s8 = new Pane();
    Pane s9 = new Pane();

    HashMap<Pane, Integer> spaces = new HashMap<>();
    HashMap<Integer, Pane> spaces2 = new HashMap<>();

    String username;
    String password;
    String email;
    boolean loggedIn = false;
    int failedAttempts;

    String serverIP;

    NaughtsAndCrossesGame currentGame;
    int xOrO;
    boolean logical;
    int[] gameboard;

    @Override
    public void start(Stage primaryStage) {

        root = new VBox();

        menuBar = new MenuBar();

        userMenu = new Menu("User");
        userMenu.getItems().addAll(login, register, changePassword, recoverPassword, logout);

        game = new Menu("Game");
        game.getItems().addAll(newGame, userStats);

        server = new Menu("Server");
        server.getItems().addAll(connect, disconnect, serverStats);

        menuBar.getMenus().addAll(userMenu, game, server);

        grid = new GridPane();
        grid.setMaxHeight(Double.MAX_VALUE);
        grid.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(menuBar, Priority.NEVER);
        VBox.setVgrow(grid, Priority.ALWAYS);
        grid.setPadding(new Insets(20, 20, 20, 20));

        double linelength = 50;
//vertical
        grid.add(new Line(0, 0, 0, linelength), 1, 0);
        grid.add(new Line(0, 0, 0, linelength), 1, 2);
        grid.add(new Line(0, 0, 0, linelength), 1, 4);

        grid.add(new Line(0, 0, 0, linelength), 3, 0);
        grid.add(new Line(0, 0, 0, linelength), 3, 2);
        grid.add(new Line(0, 0, 0, linelength), 3, 4);

//horizontal
        grid.add(new Line(0, 0, linelength, 0), 0, 1);
        grid.add(new Line(0, 0, linelength, 0), 2, 1);
        grid.add(new Line(0, 0, linelength, 0), 4, 1);

        grid.add(new Line(0, 0, linelength, 0), 0, 3);
        grid.add(new Line(0, 0, linelength, 0), 2, 3);
        grid.add(new Line(0, 0, linelength, 0), 4, 3);

//spaces
        s1.setPrefSize(50, 50);
        grid.add(s1, 0, 0);
        s1.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            playerPlaceChar(s1);
        });
        spaces.put(s1, 0);
        spaces2.put(0, s1);
        s2.setPrefSize(50, 50);
        grid.add(s2, 2, 0);
        s2.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            playerPlaceChar(s2);
        });
        spaces.put(s2, 1);
        spaces2.put(1, s2);
        s3.setPrefSize(50, 50);
        grid.add(s3, 4, 0);
        s3.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            playerPlaceChar(s3);
        });
        spaces.put(s3, 2);
        spaces2.put(2, s3);
        s4.setPrefSize(50, 50);
        grid.add(s4, 0, 2);
        s4.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            playerPlaceChar(s4);
        });
        spaces.put(s4, 3);
        spaces2.put(3, s4);
        s5.setPrefSize(50, 50);
        grid.add(s5, 2, 2);
        s5.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            playerPlaceChar(s5);
        });
        spaces.put(s5, 4);
        spaces2.put(4, s5);
        s6.setPrefSize(50, 50);
        grid.add(s6, 4, 2);
        s6.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            playerPlaceChar(s6);
        });
        spaces.put(s6, 5);
        spaces2.put(5, s6);
        s7.setPrefSize(50, 50);
        grid.add(s7, 0, 4);
        s7.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            playerPlaceChar(s7);
        });
        spaces.put(s7, 6);
        spaces2.put(6, s7);
        s8.setPrefSize(50, 50);
        grid.add(s8, 2, 4);
        s8.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            playerPlaceChar(s8);
        });
        spaces.put(s8, 7);
        spaces2.put(7, s8);
        s9.setPrefSize(50, 50);
        grid.add(s9, 4, 4);
        s9.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent mouseEvent) -> {
            playerPlaceChar(s9);
        });
        spaces.put(s9, 8);
        spaces2.put(8, s9);

        disableLoginFunctions();
        disableConnectFunctions();
        grid.disableProperty().set(true);

//==Start Logged In
        startLoggedIn();
        root.getChildren().addAll(menuBar, grid);
        Scene scene = new Scene(root, 500, 350);

//login
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> userPass = displayLoginDialog();
                //get failedAttempts from database
                if (failedAttempts >= 3) {
                    disableLoginFunctions();
                    Alerts.lockOutMessage();
                } else {
                    if (userPass.size() < 2) {

                    } else {
                        username = userPass.get(0);
                        password = userPass.get(1);
                        //if(userLoggedIn(userPass.get(0), userPass.get(1)) == true){
                        loggedIn = true;
                        enableLoginFunctions();
                        //}else{
                        //FaiedLogginMessage();
                        //failedAttempts++;
                        //write failedAttempts to database
                        //}
                    }
                }
            }
        }
        );

//register
        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean userValid = false;
                boolean passValid = false;
                boolean passwordMatching = false;
                boolean emailValid = false;
                ArrayList<String> newUserData = displayRegisterDialog();
                if (newUserData.size() < 4) {

                } else {
                    username = newUserData.get(0);
                    password = newUserData.get(1);
                    String confirmPassword = newUserData.get(2);
                    email = newUserData.get(3);
                    failedAttempts = 0;
                    if (Validation.usernameValid(username)) {
                        userValid = true;
                    } else {
                        Alerts.invalidUsernameMessage();
                    }
                    if (Validation.passwordValid(password)) {
                        passValid = true;
                    } else {
                        Alerts.invalidPasswordMessage();
                    }
                    if (Validation.passwordMatch(password, confirmPassword)) {
                        passwordMatching = true;
                    } else {
                        Alerts.passwordsDontMatchMessage();
                    }
                    if (Validation.emailValid(email)) {
                        emailValid = true;
                    } else {
                        Alerts.invalidEmailMessage();
                    }
                    //checkAll
                    if (userValid && passValid && passwordMatching && emailValid) {
                        //AddUserToDatabase(username, password, email);
                        System.out.println("New User Created");
                    }
                }
            }
        }
        );
//change Password
        changePassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
//recover password        
        recoverPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> userPass = displayLoginDialog();
                //check database
                if (userPass.size() < 2) {

                } else {
                    //check database
                    //if(userLoggedIn(userPass.get(0), userPass.get(1)) == true){
                    //send Email
                    //}
                }
            }
        }
        );

//logout
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                loggedIn = false;
                Alerts.logoutMessage();
                disableLoginFunctions();
                clearBoard();
                grid.setDisable(true);
            }
        }
        );

//new game
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //end current game(if any)
                //update user info from database

                clearBoard();
                //grab member variables
                logical = newGameDialog();
                currentGame = new NaughtsAndCrossesGame();
                gameboard = currentGame.board;
                xOrO = gameboard[10];
                grid.setDisable(false);

                if (currentGame.playerTurn == 2) {
                    if (logical) {
                        gameboard = currentGame.smartMove();
                    } else {
                        gameboard = currentGame.randomCompMove();
                    }
                }
                fillBoard();
            }
        });

//user stats
        userStats.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alerts.userStatAlert();
            }
        });

//connect
        connect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                serverIP = displayConnectDialog();
                if (serverIP.equals("")) {
                    Alerts.invalidConnectionAlert();
                    return;
                } else {
                    enableConnectFunctions();
                }
            }
        });

//disconnect
        disconnect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                disableConnectFunctions();
                Alerts.disconnectedAlert();
                clearBoard();
                grid.setDisable(true);
            }
        });

//server stats
        serverStats.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alerts.serverStatAlert();
            }
        });

//create stage
        primaryStage.setTitle("Naughts and Crosses");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

//==Methods
    //playSpace
    public void playerPlaceChar(Pane P) {
        if (currentGame.playerTurn == 1) {
            int spaceChosen = spaces.get(P);
            gameboard[spaceChosen] = 1;
            currentGame.board = gameboard;
            fillBoard();
            if (logical) {

                gameboard = currentGame.smartMove();

            } else {

                gameboard = currentGame.randomCompMove();

            }
            fillBoard();
        }
    }
//=================

    public void fillBoard() {
        clearBoard();
        for (int i = 0; i < 9; i++) {
            int space = gameboard[i];
            Pane P = spaces2.get(i);
            Label L = new Label();
            L.setFont(Font.font("System", 30));
            L.setLayoutX(15);
            L.setLayoutY(7);
            if (space == 1) {
                if (xOrO == 1) {
                    L.setText("X");
                    P.getChildren().add(L);
                    P.setDisable(true);
                }
                if (xOrO == 2) {
                    L.setText("O");
                    P.getChildren().add(L);
                    P.setDisable(true);
                }
            }
            if (space == 2) {
                if (xOrO == 2) {
                    L.setText("X");
                    P.getChildren().add(L);
                    P.setDisable(true);
                }
                if (xOrO == 1) {
                    L.setText("O");
                    P.getChildren().add(L);
                    P.setDisable(true);
                }
            }
        }
        System.out.println(currentGame.over);
        if (currentGame.over) {
            checkForWinner();
        }
        System.out.println(Arrays.toString(gameboard));
    }

    public void checkForWinner() {
        if (currentGame.checkWinner()) {
            if (currentGame.winner == 1) {
                //change score
                Alerts.NACWinAlert();
                return;
            }
            if (currentGame.winner == 2) {
                //change score
                Alerts.NACLooseAlert();
                return;
            }
            if (currentGame.winner == 0) {
                //change score
                Alerts.NACTieAlert();
                return;
            }
        }
    }

    public void clearBoard() {
        s1.getChildren().clear();
        s1.setDisable(false);
        s2.getChildren().clear();
        s2.setDisable(false);
        s3.getChildren().clear();
        s3.setDisable(false);
        s4.getChildren().clear();
        s4.setDisable(false);
        s5.getChildren().clear();
        s5.setDisable(false);
        s6.getChildren().clear();
        s6.setDisable(false);
        s7.getChildren().clear();
        s7.setDisable(false);
        s8.getChildren().clear();
        s8.setDisable(false);
        s9.getChildren().clear();
        s9.setDisable(false);
    }

    //login dialog
    public ArrayList<String> displayLoginDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login");

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);
        // validation
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);
        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());
        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        ArrayList<String> userPass = new ArrayList<>();
        result.ifPresent(usernamePassword -> {
            //System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
            userPass.add(usernamePassword.getKey());
            userPass.add(usernamePassword.getValue());
        });
        return userPass;
    }
//database check

    //public boolean CheckDataBase(String username, String password) {
    //    return true;
    //}
//register dialog
    public ArrayList<String> displayRegisterDialog() {
        Dialog<ArrayList<String>> dialog = new Dialog<>();
        dialog.setTitle("Register");

        ButtonType RegisterButtonType = new ButtonType("Register", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(RegisterButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Re-Enter Password");
        TextField email = new TextField();
        email.setPromptText("email");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);
        grid.add(new Label("Confirm:"), 0, 2);
        grid.add(confirmPassword, 1, 2);
        grid.add(new Label("Email:"), 0, 3);
        grid.add(email, 1, 3);

        // Enable/Disable login button depending on whether a username was entered.
        Node registerButton = dialog.getDialogPane().lookupButton(RegisterButtonType);
        registerButton.setDisable(true);
        //Register lock
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            registerButton.setDisable(newValue.trim().isEmpty());
        });
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            registerButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);
        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());
        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == RegisterButtonType) {
                ArrayList<String> returnVal = new ArrayList<>();
                returnVal.add(username.getText());
                returnVal.add(password.getText());
                returnVal.add(confirmPassword.getText());
                returnVal.add(email.getText());
                return returnVal;
            }
            return null;
        });
        Optional<ArrayList<String>> result = dialog.showAndWait();
        ArrayList<String> userPass = new ArrayList<>();
        result.ifPresent(usernamePassword -> {
            userPass.add(usernamePassword.get(0));
            userPass.add(usernamePassword.get(1));
            userPass.add(usernamePassword.get(2));
            userPass.add(usernamePassword.get(3));
        });
        return userPass;
    }

    public boolean newGameDialog() {
        List<String> choices = new ArrayList<>();
        choices.add("intelligent");
        choices.add("random");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("intelligent", choices);
        dialog.setTitle("New Game");
        dialog.setContentText("Choose game type:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals("intelligent")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String displayConnectDialog() {
        TextInputDialog dialog = new TextInputDialog("IP");
        dialog.setTitle("Connect");
        dialog.setHeaderText("Connect to a server");
        dialog.setContentText("Please enter server IP:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return "";
        }
    }

    public void enableLoginFunctions() {
        login.setDisable(true);
        register.setDisable(true);
        changePassword.setDisable(false);
        recoverPassword.setDisable(true);
        logout.setDisable(false);
        newGame.setDisable(false);
        userStats.setDisable(false);
    }

    public void disableLoginFunctions() {
        login.setDisable(false);
        register.setDisable(false);
        changePassword.setDisable(true);
        recoverPassword.setDisable(false);
        logout.setDisable(true);
        newGame.setDisable(true);
        userStats.setDisable(true);
    }

    public void enableConnectFunctions() {
        disableLoginFunctions();
        connect.setDisable(true);
        disconnect.setDisable(false);
        serverStats.setDisable(false);
    }

    public void disableConnectFunctions() {
        disableLoginFunctions();
        login.setDisable(true);
        register.setDisable(true);
        recoverPassword.setDisable(true);
        connect.setDisable(false);
        disconnect.setDisable(true);
        serverStats.setDisable(true);
    }

    public void startLoggedIn() {
        enableConnectFunctions();
        enableLoginFunctions();
    }

}//end of class
