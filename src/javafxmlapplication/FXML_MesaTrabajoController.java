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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class FXML_MesaTrabajoController implements Initializable {

    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    private double zoomFactor = 1.0;
    private final double zoomStep = 0.1;
    private final double zoomMin = 0.5;
    private final double zoomMax = 3.0;
    private HerramientaActiva herramientaActiva = HerramientaActiva.NINGUNA;
    private Pane drawPane;
    private Group zoomGroup;
    private double startX, startY;
    private double arcoCentroX = -1;
    private double arcoCentroY = -1;
    
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
    @FXML
    private Circle circulo1;
    @FXML
    private Circle circulo12;
    @FXML
    private Line linea;
    @FXML
    private Text texto;
    @FXML
    private ColorPicker colorElegir;

    private enum HerramientaActiva {
        NINGUNA, PUNTO, LINEA_ESPERANDO_PUNTO_FINAL, ARCO_ESPERANDO_RADIO
    }
    
    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zoom_slider.setMin(1.0);
        zoom_slider.setMax(5.0);
        zoom_slider.setValue(3.0);
        zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));

        Group contentGroup = new Group();
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(map_scrollpane.getContent());
        map_scrollpane.setContent(contentGroup);
        
        drawPane = new Pane();
        drawPane.setPickOnBounds(false);
        zoomGroup.getChildren().add(drawPane);
        
        zoomGroup.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            Circle puntoExistente = getPuntoEn(x, y);

            if (herramientaActiva == HerramientaActiva.PUNTO) {
                if (puntoExistente == null) {
                    crearPunto(x, y);
                    herramientaActiva = HerramientaActiva.NINGUNA;
                } else {
                    System.out.println("Click en punto existente");
                }
            } else if (herramientaActiva == HerramientaActiva.LINEA_ESPERANDO_PUNTO_FINAL) {
                if (startX < 0 && startY < 0) {
                    startX = x;
                    startY = y;
                    if (puntoExistente == null) {
                        crearPunto(startX, startY);
                    }else {
                        startX = puntoExistente.getCenterX();
                        startY = puntoExistente.getCenterY();
                    }
                } else {
                    double endX = x;
                    double endY = y;
                    if (puntoExistente == null) {
                        crearPunto(x, y);
                        endX = x;
                        endY = y;
                    } else {
                        endX = puntoExistente.getCenterX();
                        endY = puntoExistente.getCenterY();
                    }

                    Line lineaNueva = new Line(startX, startY, endX, endY);
                    lineaNueva.setStroke(Color.BLUE);
                    lineaNueva.setStrokeWidth(3);
                    drawPane.getChildren().add(lineaNueva);

                    herramientaActiva = HerramientaActiva.NINGUNA;
                    startX = startY = -1;
                }
            }  else if (herramientaActiva == HerramientaActiva.ARCO_ESPERANDO_RADIO) {
                if (arcoCentroX < 0 && arcoCentroY < 0) {
                    puntoExistente = getPuntoEn(x, y);
                        if (puntoExistente != null) {
                        arcoCentroX = puntoExistente.getCenterX();
                        arcoCentroY = puntoExistente.getCenterY();
                        } else {
                            arcoCentroX = x;
                            arcoCentroY = y;
                            crearPunto(arcoCentroX, arcoCentroY);
                        }
                } else {
                    Circle puntoRadio = getPuntoEn(x, y);
                    double radioX, radioY;
                    if (puntoRadio != null) {
                        radioX = puntoRadio.getCenterX();
                        radioY = puntoRadio.getCenterY();
                    } else {
                        radioX = x;
                        radioY = y;
                    }
                    double dx = radioX - arcoCentroX;
                    double dy = radioY - arcoCentroY;
                    double radio = Math.sqrt(dx*dx + dy*dy);

                    if (radio > 0) {
                        Circle arco = new Circle(arcoCentroX, arcoCentroY, radio);
                        arco.setStroke(Color.GREEN);
                        arco.setStrokeWidth(3);
                        arco.setFill(Color.TRANSPARENT);

                        drawPane.getChildren().add(arco);
                    }
                    herramientaActiva = HerramientaActiva.NINGUNA;
                    arcoCentroX = arcoCentroY = -1;
                }
            }
        });
    }    

    @FXML
    private void handlePunto(ActionEvent event) {
        herramientaActiva = HerramientaActiva.PUNTO;
    }

    @FXML
    private void handleLinea(ActionEvent event) {
        herramientaActiva = HerramientaActiva.LINEA_ESPERANDO_PUNTO_FINAL;
        startX = startY = -1;
    }

    @FXML
    private void handleArco(ActionEvent event) {
        herramientaActiva = HerramientaActiva.ARCO_ESPERANDO_RADIO;
        arcoCentroX = arcoCentroY = -1;
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
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal += 0.1);
    }

    @FXML
    void zoomOut(ActionEvent event) {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal + -0.1);
    }
    
    private void updateZoom() {
        map.setFitWidth(896 * zoomFactor);
        map.setFitHeight(576 * zoomFactor);
    }  
    
    private void zoom(double scaleValue) {
        
        double scrollH = map_scrollpane.getHvalue();
        double scrollV = map_scrollpane.getVvalue();
        
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        
        map_scrollpane.setHvalue(scrollH);
        map_scrollpane.setVvalue(scrollV);
    }
    
    private void crearPunto(double x, double y) {
        Circle punto = new Circle(x, y, 5);
        punto.setFill(Color.RED);
        punto.setStroke(Color.BLACK);

        drawPane.getChildren().add(punto);
    }
    
    private Circle getPuntoEn(double x, double y) {
        for (javafx.scene.Node node : drawPane.getChildren()) {
            if (node instanceof Circle) {
                Circle c = (Circle) node;
                double dx = c.getCenterX() - x;
                double dy = c.getCenterY() - y;
                double distancia = Math.sqrt(dx*dx + dy*dy);
                if (distancia <= c.getRadius()) {
                    return c; 
                }
            }
        }
        return null;
    }
}
