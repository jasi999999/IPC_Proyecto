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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class FXML_IniciarSesionController implements Initializable {

    private JavaFXMLApplication mainApp;

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
    private TextField passwordVisible;
    @FXML
    private Label errorLogin;

    private boolean contrasenaVisible = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        javafx.application.Platform.runLater(() -> rootPane.requestFocus());
        registrarseButton.setOnMouseReleased(event -> rootPane.requestFocus());
        iniciarSesionButton.setOnMouseReleased(event -> rootPane.requestFocus());
        rootPane.setOnMouseClicked(event -> rootPane.requestFocus());
        errorLogin.setVisible(false);
        
        // Listener para Enter
        usernameMenuInicial.setOnAction(e -> iniciarSesionButton.fire());
        passwordMenuInicial.setOnAction(e -> iniciarSesionButton.fire());
        passwordVisible.setOnAction(e -> iniciarSesionButton.fire());
    }

    @FXML
    private void menuRegistrarse(ActionEvent event) {
        try {
            mainApp.startRegistrarUsuario();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void iniciarSesionButton(ActionEvent event) {
        String nick = usernameMenuInicial.getText();
        String pass = passwordMenuInicial.isVisible() ? passwordMenuInicial.getText() : passwordVisible.getText();

        if (nick.isEmpty() || pass.isEmpty()) {
            mostrarError("Debe rellenar todos los campos.");
            return;
        }

        boolean valido = DatabaseManager.autenticarUsuario(nick, pass);
        if (valido) {
            ocultarError();
            try {
                Usuario usuario = DatabaseManager.obtenerUsuario(valido, nick, pass);
                if (usuario != null) {
                    mainApp.startMenuUsuario(usuario);
                }
            } catch (Exception e) {
                mostrarError("Error al cargar el menú.");
            }
        } else {
            mostrarError("Usuario y/o contraseña incorrectos.");
        }
    }

    private void mostrarError(String mensaje) {
        errorLogin.setText(mensaje);
        errorLogin.setVisible(true);
    }

    private void ocultarError() {
        errorLogin.setText("");
        errorLogin.setVisible(false);
    }

    @FXML
    private void handleMostrarContraseña(MouseEvent event) {
        contrasenaVisible = !contrasenaVisible;

        if (contrasenaVisible) {
            passwordVisible.setText(passwordMenuInicial.getText());
            passwordMenuInicial.setVisible(false);
            passwordMenuInicial.setManaged(false);
            passwordVisible.setVisible(true);
            passwordVisible.setManaged(true);

            URL cerrado = getClass().getResource("/icons/logo_ojo_cerrado.png");
            if (cerrado != null) {
                mostrarContraseña.setImage(new Image(cerrado.toExternalForm()));
            } else {
                System.err.println("Imagen no encontrada: logo_ojo_cerrado.png");
            }
        } else {
            passwordMenuInicial.setText(passwordVisible.getText());
            passwordVisible.setVisible(false);
            passwordVisible.setManaged(false);
            passwordMenuInicial.setVisible(true);
            passwordMenuInicial.setManaged(true);

            URL abierto = getClass().getResource("/icons/logo_ojo.jpg");
            if (abierto != null) {
                mostrarContraseña.setImage(new Image(abierto.toExternalForm()));
            } else {
                System.err.println("Imagen no encontrada: logo_ojo.jpg");
            }
        }
    }
}
