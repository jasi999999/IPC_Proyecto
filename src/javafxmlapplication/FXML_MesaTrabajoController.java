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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class FXML_MesaTrabajoController implements Initializable {

    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    
    @FXML
    private BorderPane rootPane;
    @FXML
    private Button puntoB;
    @FXML
    private Button lineaB;
    @FXML
    private Button arcoB;
    @FXML
    private Button textoB;
    @FXML
    private Button colorB;
    @FXML
    private Button eleminarElementoB;
    @FXML
    private Button limpiarB;
    @FXML
    private Button reglaB;
    @FXML
    private Button extremosB;
    @FXML
    private Button cerrarB;
    @FXML
    private ScrollPane mapScroll;
    @FXML
    private ImageView map;
    @FXML
    private Button menosZoomB;
    @FXML
    private Slider zoomBar;
    @FXML
    private Button masZoomB;

    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void handlePunto(ActionEvent event) {
    }

    @FXML
    private void handleLinea(ActionEvent event) {
    }

    @FXML
    private void handleArco(ActionEvent event) {
    }

    @FXML
    private void handleTexto(ActionEvent event) {
    }

    @FXML
    private void handleColor(ActionEvent event) {
    }

    @FXML
    private void handleEliminarElemento(ActionEvent event) {
    }

    @FXML
    private void handleLimpiar(ActionEvent event) {
    }

    @FXML
    private void handleTransportador(ActionEvent event) {
    }

    @FXML
    private void handleRegla(ActionEvent event) {
    }

    @FXML
    private void handleExtremos(ActionEvent event) {
    }

    @FXML
    private void handleCerrar(ActionEvent event) {
        ((javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    private void handleMenosZoom(ActionEvent event) {
    }

    @FXML
    private void handleMasZoom(ActionEvent event) {
    }
    
}
