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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class FXML_MenuEstadisticasController implements Initializable {

    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    
    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @FXML
    private HBox volverB;
    @FXML
    private BorderPane rootPane;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        javafx.application.Platform.runLater(() -> rootPane.requestFocus());
    }    

    @FXML
    private void handleVolverMenuUsuario(MouseEvent event) {
        try {
            mainApp.startIniciarSesion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
