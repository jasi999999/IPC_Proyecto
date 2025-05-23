/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.regex.Pattern;
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
import java.time.LocalDate;
import java.time.Period;


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
    private TextField usernameRegistro; // No editable
    @FXML
    private Text mensajeErrorRegistro;
    @FXML
    private TextField correoElectronicoPerfil;
    @FXML
    private PasswordField passwordFieldPerfil;
    @FXML
    private DatePicker fechaNacimientoPerfil;
    @FXML
    private ImageView imagenPerfil;
    
    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    @FXML
    private Button continuarButton;
    @FXML
    private Button cancelarButtonPerfil;
    @FXML
    private Button subirFotoPerfil;
    @FXML
    private Button eliminarFotoPerfil;

    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
        cargarDatosUsuario();
    }
    
    private void cargarDatosUsuario() {
        if (usuario != null) {
            usernameRegistro.setText(usuario.getNick());
            correoElectronicoPerfil.setText(usuario.getEmail());
            passwordFieldPerfil.setText(usuario.getPassword());
            fechaNacimientoPerfil.setValue(usuario.getFechaNacimiento());
            // Aquí podrías cargar la imagen si tienes una ruta o blob guardado
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        javafx.application.Platform.runLater(() -> rootPane.requestFocus());
        rootPane.setOnMouseClicked(event -> rootPane.requestFocus());
        // Para el campo de username
        usernameRegistro.setEditable(false);      // No permite modificar el texto
        usernameRegistro.setFocusTraversable(false); // Evita que reciba foco al hacer clic
        usernameRegistro.setMouseTransparent(true);  // Ignora eventos de mouse (clic)

        mensajeErrorRegistro.setVisible(false);
    }

    @FXML
    private void handleContinuarButton(ActionEvent event) {
        String email = correoElectronicoPerfil.getText();
        String pass = passwordFieldPerfil.getText();
        LocalDate birthDate = fechaNacimientoPerfil.getValue();

        if (email.isEmpty() || pass.isEmpty() || birthDate == null) {
            mostrarError("Todos los campos son obligatorios.");
            return;
        }
        
        String errorEmail = validarEmail(email);
        if (errorEmail != null) {
            mostrarError("Correo inválido: " + errorEmail);
            return;
        }

        String errorPass = validarPassword(pass);
        if (errorPass != null) {
            mostrarError("Contraseña insegura: " + errorPass);
            return;
        }

        if (!mayorDe16Anios(birthDate)) {
            mostrarError("Debes tener al menos 16 años.");
            return;
        }

        // Guardar cambios en la base de datos
        boolean exito = DatabaseManager.modificarPerfil(usernameRegistro.getText(), email, pass, birthDate);
        if (!exito) {
            mostrarError("Error al actualizar el perfil.");
            return;
        }

        ocultarError();
        volverMenuUsuario(event);
    }


    @FXML
    private void handleEliminarFotoRegistro(ActionEvent event) {
        imagenPerfil.setImage(null);
    }

    @FXML
    private void volverMenuUsuario(ActionEvent event) {
        try {
            mainApp.startMenuUsuario(usuario); // o como sea que se navega
        } catch (Exception e) {
            mostrarError("No se pudo volver al menú del usuario.");
        }
    }
    
    private void mostrarError(String msg) {
        mensajeErrorRegistro.setText(msg);
        mensajeErrorRegistro.setVisible(true);
    }

    private void ocultarError() {
        mensajeErrorRegistro.setText("");
        mensajeErrorRegistro.setVisible(false);
    }
    
    private String validarEmail(String email) {
        if (!Pattern.matches("^[\\w.-]+@[\\w-]+\\.[\\w]{2,}$", email)) {
            return "Formato incorrecto. Ejemplo: usuario@dominio.com";
        }
        return null;
    }

    private String validarPassword(String pass) {
        if (pass.length() < 8 || pass.length() > 20) {
            return "Debe tener entre 8 y 20 caracteres.";
        }
        if (!pass.matches(".*[a-z].*")) {
            return "Debe incluir al menos una letra minúscula.";
        }
        if (!pass.matches(".*[A-Z].*")) {
            return "Debe incluir al menos una letra mayúscula.";
        }
        if (!pass.matches(".*\\d.*")) {
            return "Debe incluir al menos un número.";
        }
        if (!pass.matches(".*[!@#$%&*()\\-+=].*")) {
            return "Debe incluir al menos un carácter especial (!@#$%&*()-+=).";
        }
        return null;
    }

    private boolean mayorDe16Anios(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 16;
    }

    @FXML
    private void handleSubirFotoRegistro(ActionEvent event) {
    }
}