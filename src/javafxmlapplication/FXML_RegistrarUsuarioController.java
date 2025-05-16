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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class FXML_RegistrarUsuarioController implements Initializable {

    // Referencia a la instancia principal de aplicación
    private JavaFXMLApplication mainApp;
    
    // Método para establecer la referencia a la clase principal
    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private Text mensajeErrorRegistro;
    @FXML
    private Button continuarButton;
    @FXML
    private ImageView imagenPerfilRegistro;
    @FXML
    private Button subirFotoRegistro;
    @FXML
    private Button eliminarFotoRegistro;
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
        
        // Al hacer clic en el panel, darle el foco
        rootPane.setOnMouseClicked(event -> rootPane.requestFocus());
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
    private void handleContinuarButton(ActionEvent event) {
        // Registrar user y volver al Iniciar Sesión
        // Checkear que los formatos de datos son correctos y guardar en BD
        
        // Si se cumple lo anterior do:
        try {
            mainApp.startIniciarSesion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSubirFotoRegistro(ActionEvent event) {
        // Subir una foto al Imagen View y guardarla en BD
    }

    @FXML
    private void handleEliminarFotoRegistro(ActionEvent event) {
        // Eliminar la foto del ImagenView y de la BD.
    }
    
}
