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

/**
 * Mismo archivo y controlador que el de Registrar usuario, pero con la selección
 * del campo useranme -> TextField -> Properties -> Editable desactivada.
 * Posibilidad de eliminar este campo y reutilizar el "RegistrarUsuario". 
 * 
 */
public class FXML_ModificarPerfilController implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private TextField usernameRegistro;
    @FXML
    private Text mensajeErrorRegistro;
    @FXML
    private Button continuarButton;
    @FXML
    private TextField correoElectronicoPerfil;
    @FXML
    private PasswordField passwordFieldPerfil;
    @FXML
    private DatePicker fechaNacimientoPerfil;
    @FXML
    private Button cancelarButtonPerfil;
    @FXML
    private ImageView imagenPerfil;
    @FXML
    private Button subirFotoPerfil;
    @FXML
    private Button eliminarFotoPerfil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    @FXML
    private void handleContinuarButton(ActionEvent event) {
        // Checkear que los formatos de datos son correctos y guardar en BD
        // Si se cumple lo anterior volver al Menú Usuario
    }

    @FXML
    private void handleSubirFotoRegistro(ActionEvent event) {
    }

    @FXML
    private void handleEliminarFotoRegistro(ActionEvent event) {
    }

    @FXML
    private void volverMenuUsuario(ActionEvent event) {
    }
    
}
