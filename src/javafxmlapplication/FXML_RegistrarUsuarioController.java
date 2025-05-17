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

        if (!validarNickname(nick)) {
            mostrarError("Nombre de usuario inválido (6-15 caracteres, sin espacios).");
            return;
        }

        if (!validarCorreo(email)) {
            mostrarError("Correo electrónico inválido.");
            return;
        }

        if (!validarPassword(pass)) {
            mostrarError("Contraseña inválida. 8-20 caracteres,\nmayús., minús., número y símbolo.");
            return;
        }

        if (!mayorDe16Anios(birthDate)) {
            mostrarError("Debes tener al menos 16 años.");
            return;
        }

        if (UsuarioManager.existeNick(nick)) {
            mostrarError("El nombre de usuario ya está registrado.");
            return;
        }

        UsuarioSimulado nuevo = new UsuarioSimulado(nick, email, pass);
        UsuarioManager.registrarUsuario(nuevo);

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

    private boolean validarNickname(String nick) {
        return nick.length() >= 6 && nick.length() <= 15 && !nick.contains(" ");
    }

    private boolean validarCorreo(String email) {
        return Pattern.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }

    private boolean validarPassword(String pass) {
        return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*()\\-+=]).{8,20}$", pass);
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
