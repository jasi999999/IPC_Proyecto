package javafxmlapplication;

import java.sql.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import model.Problem;
import model.Answer;
import model.Navigation;
import model.NavDAOException;

public class FXML_MenuProblemaController implements Initializable {

    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    private List<Problem> problemas;
    private int indexActual = -1;
    private static final String DB_URL = "jdbc:sqlite:usuarios.db";
    
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

    @FXML
    private void handleResponder(ActionEvent event) {
        RadioButton seleccionada = (RadioButton) respuestasGroup.getSelectedToggle();
        if (seleccionada == null) {
            return;
            }
        String textoRespuesta = seleccionada.getText();

        Problem problemaActual = problemas.get(indexActual);

        boolean esCorrecta = false;
        for (Answer a : problemaActual.getAnswers()) {
            if (a.getText().equals(textoRespuesta) && a.getValidity()) {
                esCorrecta = true;
                break;
            }
        }

        if (esCorrecta) {
            System.out.println("Respuesta correcta!");
            guardarEstadistica(usuario.getNick(), true);
        } else {
            System.out.println("Respuesta incorrecta.");
            guardarEstadistica(usuario.getNick(), false);
        }
        
        try {
            mainApp.startMenuUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setIndexProblema(int index) {
        if (index < 0) return;
        this.indexActual = index;
        
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
    
    public void guardarEstadistica(String nick, boolean acierto) {
        String resultado = acierto ? "acierto" : "fallo";
        String fecha = java.time.LocalDate.now().toString();

        String sql = "INSERT INTO usuarios_estadisticas (nick, resultado, fecha) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nick);
            pstmt.setString(2, resultado);
            pstmt.setString(3, fecha);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
