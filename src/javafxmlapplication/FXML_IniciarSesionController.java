/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import com.gluonhq.charm.glisten.control.Avatar;
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
import javafx.scene.layout.BorderPane;

/**
 *
 * @author jsoler
 */
public class FXML_IniciarSesionController implements Initializable {
    
    private Label labelMessage;
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
    private Label errorLogin;
    
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    private void handleButtonAction(ActionEvent event) {
        labelMessage.setText("Hello, this is your first JavaFX project - IPC");
    }
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        registrarseButton.setOnMouseReleased(event -> {
        rootPane.requestFocus();  // quitar focus del botón al soltar
        });
        iniciarSesionButton.setOnMouseReleased(event -> {
        rootPane.requestFocus();  // quitar focus del botón al soltar
        });

        System.out.println("Hello 3");
    }    

    @FXML
    private void menuRegistrarse(ActionEvent event) {
    }

    @FXML
    private void iniciarSesionButton(ActionEvent event) {
    }
    
}
