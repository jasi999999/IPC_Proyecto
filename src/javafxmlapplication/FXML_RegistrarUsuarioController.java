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
public class FXML_RegistrarUsuarioController implements Initializable {

    // Referencia a la instancia principal de aplicación
    private JavaFXMLApplication mainApp;
    @FXML
    private Button validarButton;
    
    // Método para establecer la referencia a la clase principal
    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private BorderPane rootPane;
    @FXML
    private TextField usernameRegistro;             // no repetido, 6-15, no espacios, sí "-" o "_"
    @FXML
    private PasswordField passwordFieldRegistro;    // 8-20, 1 may, 1 mín, 1 díg, 1 char (!@#$%&*()-+=)
    @FXML
    private Button cancelarButton;
    @FXML
    private TextField correoElectronicoRegistro;    // Formato válido
    @FXML
    private DatePicker fechaNacimiento;             // +16 años

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Evita que el focus se centre en el TextField de nickname, el cual
        // impide que se vea el cambo predeterminado "jpgarcia"
        javafx.application.Platform.runLater(() -> rootPane.requestFocus());
    }    

    @FXML
    private void volverMenuInicial(ActionEvent event) {
        try {
            mainApp.startIniciarSesion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleValidarButton(ActionEvent event) {
    }
    
}
