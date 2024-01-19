/*
 * Author  : Mr.electrix
 * Project : liveChatRoom
 * Date    : 1/17/24

 */

package lk.ijse.liveChatRoom.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.liveChatRoom.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private TextField txtUserName;
    @FXML
    public void btnLoginOnAction(MouseEvent mouseEvent) throws IOException {
        if (!txtUserName.getText().isEmpty()&&txtUserName.getText().matches("[A-Za-z0-9]+")){
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/client_form.fxml"));

            ClientFormController controller = new ClientFormController();
            controller.setClientName(txtUserName.getText()); // Set the parameter
            fxmlLoader.setController(controller);

            primaryStage.setScene(new Scene(fxmlLoader.load()));
            primaryStage.setTitle(txtUserName.getText());
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.setOnCloseRequest(windowEvent -> {
                controller.shutdown();
            });
            primaryStage.show();

            txtUserName.clear();
        }else{
            new Alert(Alert.AlertType.ERROR, "Please enter your name").show();
        }
//        try {
//            String userName = txtUserName.getText().trim();
//            boolean validUser = isValidUser(userName);
//            if (validUser){
//
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
    private boolean isValidUser(String userName) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE user_name = ?");
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }
}
