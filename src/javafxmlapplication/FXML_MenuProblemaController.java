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
    private ComboBox<String> problemasList;
    
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

    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            problemas = Navigation.getInstance().getProblems();

            for (int i = 0; i < problemas.size(); i++) {
                problemasList.getItems().add("Problema #" + (i + 1));
            }

            problemasList.setOnAction(e -> mostrarProblema(problemasList.getSelectionModel().getSelectedIndex()));

        } catch (NavDAOException e) {
            System.err.println("Error cargando los problemas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarProblema(int index) {
        if (index < 0 || index >= problemas.size()) return;

        Problem p = problemas.get(index);
        List<Answer> respuestas = p.getAnswers();

        enunciadoProblema.setText(p.getText());

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
    }
}
