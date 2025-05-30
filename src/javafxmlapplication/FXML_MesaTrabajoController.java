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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class FXML_MesaTrabajoController implements Initializable {

    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    private double zoomFactor = 1.0;
    private final double zoomStep = 0.1;
    private final double zoomMin = 0.5;
    private final double zoomMax = 3.0;
    
    // la variable zoomGroup se utiliza para dar soporte al zoom
    // el escalado se realiza sobre este nodo, al escalar el Group no mueve sus nodos
    private Group zoomGroup;
    
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
    private ImageView map;
    @FXML
    private Button menosZoomB;
    @FXML
    private Slider zoom_slider;
    @FXML
    private Button masZoomB;
    @FXML
    private ScrollPane map_scrollpane;

    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // inicializamos el slider y enlazamos con el zoom
        zoom_slider.setMin(1.0);
        zoom_slider.setMax(5.0);
        zoom_slider.setValue(3.0);
        zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));

        //Envuelva el contenido de scrollpane en un grupo para que 
        //ScrollPane vuelva a calcular las barras de desplazamiento tras el escalado
        Group contentGroup = new Group();
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(map_scrollpane.getContent());
        map_scrollpane.setContent(contentGroup);
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
    void zoomIn(ActionEvent event) {
        //================================================
        // el incremento del zoom dependerá de los parametros del 
        // slider y del resultado esperado
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal += 0.1);
    }

    @FXML
    void zoomOut(ActionEvent event) {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal + -0.1);
    }
    
    private void updateZoom() {
        map.setFitWidth(896 * zoomFactor);  // el tamaño original del ImageView
        map.setFitHeight(576 * zoomFactor);
    }  
    
    // esta funcion es invocada al cambiar el value del slider zoom_slider
    private void zoom(double scaleValue) {
        //===================================================
        //guardamos los valores del scroll antes del escalado
        double scrollH = map_scrollpane.getHvalue();
        double scrollV = map_scrollpane.getVvalue();
        //===================================================
        // escalamos el zoomGroup en X e Y con el valor de entrada
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        //===================================================
        // recuperamos el valor del scroll antes del escalado
        map_scrollpane.setHvalue(scrollH);
        map_scrollpane.setVvalue(scrollV);
    }
}
