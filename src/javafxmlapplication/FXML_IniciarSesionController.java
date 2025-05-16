/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author jsoler
 */
public class FXML_IniciarSesionController implements Initializable {
    
    // Referencia a la instancia principal de aplicación
    private JavaFXMLApplication mainApp;
    
    // Método para establecer la referencia a la clase principal
    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private ImageView mostrarContraseña;
    @FXML
    private BorderPane rootPane;
    @FXML
    private Button registrarseButton;
    @FXML
    private Button iniciarSesionButton;
    @FXML
    private TextField usernameMenuInicial;      
    @FXML
    private PasswordField passwordMenuInicial;  
    @FXML
    private Label errorLogin;
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Evita que el focus se centre en el TextField de nickname, el cual
        // impide que se vea el cambo predeterminado "Usuario"
        javafx.application.Platform.runLater(() -> rootPane.requestFocus());
        // Para evitar que el botón se quede pulsado
        registrarseButton.setOnMouseReleased(event -> {
            rootPane.requestFocus();  // quitar focus del botón al soltar
        });
        iniciarSesionButton.setOnMouseReleased(event -> {
            rootPane.requestFocus();  // quitar focus del botón al soltar
        });
        
        rootPane.setOnMouseClicked(event -> rootPane.requestFocus());
    }    

    @FXML
    private void menuRegistrarse(ActionEvent event) {
        // Dispara el menú de Registro de mainApp 
        try {
            mainApp.startRegistrarUsuario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void iniciarSesionButton(ActionEvent event) {
        // Checkear con la BD que los datos username vs. contraseña son
        // correctos, en caso contrario mostrar "errorLogin".
        // If (correcto) desplegar el menu usuario.
    }

    @FXML
    private void handleMostrarContraseña(MouseEvent event) {
        // Hacer desaparecer la ImageView del ojo y mostrar la contraseña
    }
    
}
