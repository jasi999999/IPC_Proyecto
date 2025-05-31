package javafxmlapplication;

import java.sql.*;
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

public class FXML_MenuExamenController implements Initializable {
    
    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    private List<Problem> problemas;
    private int indexActual = -1;
    private static final String DB_URL = "jdbc:sqlite:usuarios.db";
    
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
            indexActual = index;
            mostrarProblema(index);
        } catch (NavDAOException e) {
            System.err.println("Error cargando los problemas: " + e.getMessage());
        }
    }   
    
    @FXML
    private void handleResponder(ActionEvent event) {
        RadioButton seleccionada = (RadioButton) respuestasGroup.getSelectedToggle();
        if (seleccionada == null) return;

        String textoSeleccionado = "";
        Text textoSeleccionadoControl = null;

        if (valid1.isSelected()) {
            textoSeleccionado = respuesta1.getText().trim();
            textoSeleccionadoControl = respuesta1;
        } else if (valid2.isSelected()) {
            textoSeleccionado = respuesta2.getText().trim();
            textoSeleccionadoControl = respuesta2;
        } else if (valid3.isSelected()) {
            textoSeleccionado = respuesta3.getText().trim();
            textoSeleccionadoControl = respuesta3;
        } else if (valid4.isSelected()) {
            textoSeleccionado = respuesta4.getText().trim();
            textoSeleccionadoControl = respuesta4;
        }

        Problem problemaActual = problemas.get(indexActual);
        List<Answer> respuestas = problemaActual.getAnswers();

        boolean esCorrecta = false;
        Text textoCorrectoControl = null;

        for (int i = 0; i < respuestas.size(); i++) {
            Answer a = respuestas.get(i);
            String textoRespuesta = a.getText().trim();

            System.out.println("Comparando:");
            System.out.println("→ seleccionado: " + textoSeleccionado);
            System.out.println("→ respuesta DB: " + textoRespuesta);

            if (a.getValidity()) {
                
                textoCorrectoControl = switch (i) {
                    case 0 -> respuesta1;
                    case 1 -> respuesta2;
                    case 2 -> respuesta3;
                    case 3 -> respuesta4;
                    default -> null;
                };
            }

            
            if (textoRespuesta.equalsIgnoreCase(textoSeleccionado) && a.getValidity()) {
                esCorrecta = true;
            }
        }

        
        if (textoCorrectoControl != null) {
            textoCorrectoControl.setStyle("-fx-fill: green;");
        }
        if (textoSeleccionadoControl != null && textoSeleccionadoControl != textoCorrectoControl) {
            textoSeleccionadoControl.setStyle("-fx-fill: red;");
        }

        
        DatabaseManager.registrarResultado(usuario.getNick(), esCorrecta);

        System.out.println(esCorrecta ? "✅ Respuesta correcta" : "❌ Respuesta incorrecta");

        
        responderB.setDisable(true);
        atrasB.setDisable(false);
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
            Scene scene = new Scene(root);
            modalStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/icons/protractor.css").toExternalForm());

            modalStage.setTitle("Mesa de Trabajo");
            modalStage.setMinWidth(1145);
            modalStage.setMinHeight(675);
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

