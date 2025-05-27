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
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class FXML_MesaTrabajoController implements Initializable {

    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    private double zoomFactor = 1.0;
    private final double zoomStep = 0.1;
    private final double zoomMin = 0.5;
    private final double zoomMax = 3.0;
    
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
    @FXML
    private Group imageGroup;

    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zoomBar.setMin(zoomMin);
        zoomBar.setMax(zoomMax);
        zoomBar.setValue(zoomFactor);
        zoomBar.setBlockIncrement(zoomStep);
        zoomBar.valueProperty().addListener((obs, oldVal, newVal) -> {
            zoomFactor = newVal.doubleValue();
            updateZoom();
        });
        mapScroll.requestFocus();
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
        if (zoomFactor - zoomStep >= zoomMin) {
            zoomFactor -= zoomStep;
            zoomBar.setValue(zoomFactor);
        }
    }

    @FXML
    private void handleMasZoom(ActionEvent event) {
        if (zoomFactor + zoomStep <= zoomMax) {
            zoomFactor += zoomStep;
            zoomBar.setValue(zoomFactor);
        }
    }
    
    private void updateZoom() {
        map.setFitWidth(896 * zoomFactor);  // el tamaño original del ImageView
        map.setFitHeight(576 * zoomFactor);
    }   
}
