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
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class FXML_MenuUsuarioController implements Initializable {

    private JavaFXMLApplication mainApp;

    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        javafx.application.Platform.runLater(() -> rootPane.requestFocus());
    }    

    @FXML
    private void handleModoExamen(ActionEvent event) {
    }

    @FXML
    private void handleElegirProblema(ActionEvent event) {
    }

    @FXML
    private void handleModificarPerfil(ActionEvent event) {
        try {
            mainApp.startModificarPerfil();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEstadisticas(ActionEvent event) {
        try {
            mainApp.startMenuEstadisticas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCerrarSesion(ActionEvent event) {
        try {
            mainApp.startIniciarSesion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
