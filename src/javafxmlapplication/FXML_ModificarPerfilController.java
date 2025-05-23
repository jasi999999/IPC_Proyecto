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
import java.io.ByteArrayInputStream;



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
    @FXML
    private Button continuarButton;
    @FXML
    private Button cancelarButtonPerfil;
    @FXML
    private Button subirFoto;
    @FXML
    private Button eliminarFoto;
    @FXML
    private Text textoFoto;

    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    private byte[] imagenBytes;
 
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

            if (usuario.getImagen() != null) {
                imagenBytes = usuario.getImagen();  // sincronizar bytes
                javafx.scene.image.Image imagen = new javafx.scene.image.Image(new ByteArrayInputStream(imagenBytes));
                imagenPerfil.setImage(imagen);
                textoFoto.setText("");
                
            } else {
                javafx.scene.image.Image defaultImage = new javafx.scene.image.Image(
                    getClass().getResource("/icons/avatar_usuario.jpg").toExternalForm()
                );
                imagenPerfil.setImage(defaultImage);
                textoFoto.setText("Insertar imagen");
            }
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
        actualizarEstadoBotonEliminarFoto();
        
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
        boolean exito = DatabaseManager.modificarPerfil(usernameRegistro.getText(), email, pass, birthDate, imagenBytes);
        if (!exito) {
            mostrarError("Error al actualizar el perfil.");
            return;
        }
        
        usuario.setImagen(imagenBytes);
        ocultarError();
        volverMenuUsuario(event);
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
    private void handleSubirFoto(ActionEvent event) {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Seleccionar imagen de perfil");
        fileChooser.getExtensionFilters().addAll(
            new javafx.stage.FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        java.io.File file = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        if (file != null) {
            try {
                // Leer archivo en bytes
                byte[] imageBytes = java.nio.file.Files.readAllBytes(file.toPath());

                // Guardar en un atributo para luego enviar a registrarUsuario
                this.imagenBytes = imageBytes;

                // Mostrar imagen en ImageView
                javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toString());
                imagenPerfil.setImage(image);
                textoFoto.setText("");
            } catch (java.io.IOException e) {
                mostrarError("Error al cargar la imagen: " + e.getMessage());
            }
        }
    }
    
    @FXML
    private void handleEliminarFoto(ActionEvent event) {
        imagenBytes = null;
        
        javafx.scene.image.Image defaultImage = new javafx.scene.image.Image(
            getClass().getResource("/icons/avatar_usuario.jpg").toExternalForm()
        );
        
        imagenPerfil.setImage(defaultImage);
        textoFoto.setText("Insertar imagen");
        actualizarEstadoBotonEliminarFoto();
    }
    
    private void actualizarEstadoBotonEliminarFoto() {
        if (imagenBytes == null) {
            eliminarFoto.setDisable(true);  
        } else {
            eliminarFoto.setDisable(false); 
        }
    }
}