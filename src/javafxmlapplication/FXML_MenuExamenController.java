/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Answer;
import model.NavDAOException;
import model.Navigation;
import model.Problem;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class FXML_MenuExamenController implements Initializable {
    
    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    private List<Problem> problemas;
    
    @FXML
    private Text enunciadoProblema;
    @FXML
    private RadioButton valid1;
    @FXML
    private ToggleGroup respuestasGroup;
    @FXML
    private Text respuesta1;
    @FXML
    private RadioButton valid2;
    @FXML
    private Text respuesta2;
    @FXML
    private RadioButton valid3;
    @FXML
    private Text respuesta3;
    @FXML
    private RadioButton valid4;
    @FXML
    private Text respuesta4;
    @FXML
    private Button responderB;
    @FXML
    private Button mesaTrabajoB;
    @FXML
    private Button atrasB;
    
    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @FXML
    private BorderPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Listeners, si click -> opción marcada
        respuesta1.setOnMouseClicked(e -> valid1.setSelected(true));
        respuesta2.setOnMouseClicked(e -> valid2.setSelected(true));
        respuesta3.setOnMouseClicked(e -> valid3.setSelected(true));
        respuesta4.setOnMouseClicked(e -> valid4.setSelected(true));
        
        mostrarProblemaAleatorio();
    }

    private void mostrarProblema(int index) {
        if (index < 0 || index >= problemas.size()) return;

        Problem p = problemas.get(index);
        List<Answer> respuestas = p.getAnswers();

        enunciadoProblema.setText("Problema #" + (index + 1) + ":\n" + p.getText());

        if (respuestas.size() >= 4) {
            respuesta1.setText(respuestas.get(0).getText());
            respuesta2.setText(respuestas.get(1).getText());
            respuesta3.setText(respuestas.get(2).getText());
            respuesta4.setText(respuestas.get(3).getText());

            valid1.setSelected(false);
            valid2.setSelected(false);
            valid3.setSelected(false);
            valid4.setSelected(false);
        }
    }   

    public void mostrarProblemaAleatorio() {
        try {
            problemas = Navigation.getInstance().getProblems();
            if (problemas == null || problemas.isEmpty()) {
                System.err.println("No hay problemas disponibles.");
                return;
            }
            Random rand = new Random();
            int index = rand.nextInt(problemas.size());
            mostrarProblema(index);
        } catch (NavDAOException e) {
            System.err.println("Error cargando los problemas: " + e.getMessage());
        }
    }   
    
    @FXML
    private void handleResponder(ActionEvent event) {
    }

    @FXML
    private void handleMesaTrabajo(ActionEvent event) {
        try {
            showMesaTrabajoModal(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleAtras(ActionEvent event) {
        try {
            mainApp.startMenuUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setIndexProblema(int index) {
        if (index < 0) return;
        
        try {
            problemas = Navigation.getInstance().getProblems();
            if (index < problemas.size()) {
                mostrarProblema(index);
            }
        } catch (NavDAOException e) {
            System.err.println("Error cargando los problemas: " + e.getMessage());
        }
    }
    
    @FXML
    public void showMesaTrabajoModal(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_MesaTrabajo.fxml"));
            Parent root = loader.load();

            FXML_MesaTrabajoController controller = loader.getController();
            controller.setMainApp(mainApp);
            controller.setUsuario(usuario);

            Stage modalStage = new Stage();
            modalStage.setScene(new Scene(root));
            modalStage.setTitle("Mesa de Trabajo");
            modalStage.setMinWidth(620);
            modalStage.setMinHeight(560);
            modalStage.initModality(Modality.APPLICATION_MODAL);

            Stage ownerStage = (Stage) rootPane.getScene().getWindow();
            modalStage.initOwner(ownerStage);

            modalStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}

