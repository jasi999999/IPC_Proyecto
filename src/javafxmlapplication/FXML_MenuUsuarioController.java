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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import java.io.ByteArrayInputStream;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class FXML_MenuUsuarioController implements Initializable {

    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    
    @FXML
    private BorderPane rootPane;
    @FXML
    private Button modoExamenB;
    @FXML
    private Button elegirProblemaB;
    @FXML
    private Button modificarPerfilB;
    @FXML
    private Button estadisticasB;
    @FXML
    private Button cerrarSesionB;
    @FXML
    private ImageView imagenUser;
    @FXML
    private Label bienvenidaName;

    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        bienvenidaName.setText(usuario.getNick() + "!");
        
        byte[] imagenBytes = usuario.getImagen(); 
            if (imagenBytes != null) {
            Image imagen = new Image(new ByteArrayInputStream(imagenBytes));
            imagenUser.setImage(imagen);
        } else {
            Image defaultImage = new Image(getClass().getResource("/icons/avatar_usuario.jpg").toExternalForm());
            imagenUser.setImage(defaultImage);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        javafx.application.Platform.runLater(() -> rootPane.requestFocus());
    }    

    @FXML
    private void handleModoExamen(ActionEvent event) {
        try {
            mainApp.startMenuExamen(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleElegirProblema(ActionEvent event) {
        try {
            mainApp.startMenuElegirProblema(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleModificarPerfil(ActionEvent event) {
        try {
            mainApp.startModificarPerfil(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEstadisticas(ActionEvent event) {
        try {
            mainApp.startMenuEstadisticas(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCerrarSesion(ActionEvent event) {
        try {
            usuario = null;
            bienvenidaName.setText("");
            mainApp.startIniciarSesion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
