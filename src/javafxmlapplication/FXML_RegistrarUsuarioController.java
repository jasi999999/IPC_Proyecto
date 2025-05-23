package javafxmlapplication;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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

public class FXML_RegistrarUsuarioController implements Initializable {

    private JavaFXMLApplication mainApp;

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
    private TextField usernameRegistro;
    @FXML
    private PasswordField passwordFieldRegistro;
    @FXML
    private Button cancelarButton;
    @FXML
    private TextField correoElectronicoRegistro;
    @FXML
    private DatePicker fechaNacimiento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        javafx.application.Platform.runLater(() -> rootPane.requestFocus());
        rootPane.setOnMouseClicked(event -> rootPane.requestFocus());
        mensajeErrorRegistro.setVisible(false);
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
        String nick = usernameRegistro.getText();
        String email = correoElectronicoRegistro.getText();
        String pass = passwordFieldRegistro.getText();
        LocalDate birthDate = fechaNacimiento.getValue();

        if (nick.isEmpty() || email.isEmpty() || pass.isEmpty() || birthDate == null) {
            mostrarError("Todos los campos son obligatorios.");
            return;
        }

        String errorNick = validarNickname(nick);
        if (errorNick != null) {
            mostrarError("Nombre de usuario inválido: " + errorNick);
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

        boolean exito = DatabaseManager.registrarUsuario(nick, email, pass, birthDate);
        if (!exito) {
            mostrarError("El nombre de usuario ya existe.");
            return;
        }

        ocultarError();
        try {
            mainApp.startIniciarSesion();
        } catch (Exception e) {
            mostrarError("Error al volver al login.");
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

    private String validarNickname(String nick) {
        if (nick.length() < 6 || nick.length() > 15) {
            return "Debe tener entre 6 y 15 caracteres.";
        }
        if (!nick.matches("^[a-zA-Z0-9_-]+$")) {
            return "Solo se permiten letras, números, guiones y guiones bajos.";
        }
        return null;
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
        // pendiente
    }

    @FXML
    private void handleEliminarFotoRegistro(ActionEvent event) {
        imagenPerfilRegistro.setImage(null);
    }
}