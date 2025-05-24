package javafxmlapplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import model.Problem;
import model.Answer;
import model.Navigation;
import model.NavDAOException;

public class FXML_MenuProblemaController implements Initializable {

    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    private List<Problem> problemas;
    
    @FXML
    private Text enunciadoProblema;
    @FXML
    private Text respuesta1;
    @FXML
    private Text respuesta2;
    @FXML
    private Text respuesta3;
    @FXML
    private Text respuesta4;

    @FXML
    private RadioButton valid1;
    @FXML
    private RadioButton valid2;
    @FXML
    private RadioButton valid3;
    @FXML
    private RadioButton valid4;
    
    @FXML
    private Button atrasB;
    @FXML
    private BorderPane rootPane;
    @FXML
    private ToggleGroup respuestasGroup;
    @FXML
    private Button mesaTrabajoB;
    @FXML
    private Button responderB;
    @FXML
    private Button problemasB;

    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Listeners, si click -> opción marcada
        respuesta1.setOnMouseClicked(e -> valid1.setSelected(true));
        respuesta2.setOnMouseClicked(e -> valid2.setSelected(true));
        respuesta3.setOnMouseClicked(e -> valid3.setSelected(true));
        respuesta4.setOnMouseClicked(e -> valid4.setSelected(true));
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

    @FXML
    private void handleMesaTrabajo(ActionEvent event) {
        try {
            mainApp.startMesaTrabajo(usuario);
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

    @FXML
    private void handleResponder(ActionEvent event) {
        // 
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
    private void handleProblemas(ActionEvent event) {
        try {
            mainApp.startMenuElegirProblema(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
