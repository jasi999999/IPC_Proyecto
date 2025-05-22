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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class FXML_MenuProblemaController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private TextField usernameRegistro;
    @FXML
    private PasswordField passwordFieldRegistro;
    @FXML
    private Button registrarUsuario;
    @FXML
    private Button cancelarButton;
    @FXML
    private TextField correoElectronicoRegistro;
    @FXML
    private DatePicker fechaNacimiento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleResgistrarUsuario(ActionEvent event) {
    }

    @FXML
    private void volverMenuInicial(ActionEvent event) {
    }
    
}
