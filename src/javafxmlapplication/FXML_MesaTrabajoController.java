/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class FXML_MesaTrabajoController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private TextField usernameMenuInicial;
    @FXML
    private PasswordField passwordMenuInicial;
    @FXML
    private Button registrarseButton;
    @FXML
    private Button iniciarSesionButton;
    @FXML
    private Label errorLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void menuRegistrarse(ActionEvent event) {
    }

    @FXML
    private void iniciarSesionButton(ActionEvent event) {
    }
    
}
