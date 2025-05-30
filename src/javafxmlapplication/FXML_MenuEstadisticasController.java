/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import javafx.geometry.Side;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class FXML_MenuEstadisticasController implements Initializable {

    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private LineChart<String, Number> chart;
    @FXML
    private DatePicker fechaInicio;
    @FXML
    private DatePicker fechaFin;
    @FXML
    private Button volverB;
    @FXML
    private BorderPane rootPane;
    @FXML
    private Button mostrarB;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ListView<String> listView;
    @FXML
    private Label mensajeError;
    
    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        javafx.application.Platform.runLater(() -> rootPane.requestFocus());
    }    

    @FXML
    private void handleVolverMenuUsuario(ActionEvent event) {
        try {
            mainApp.startMenuUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void cargarDatosEstadisticas() {
        if (usuario == null) return;

        LocalDate hoy = LocalDate.now();
        LocalDate inicio = fechaInicio.getValue() != null ? fechaInicio.getValue() : hoy;
        LocalDate fin = fechaFin.getValue() != null ? fechaFin.getValue() : hoy;

        // Validación de fechas
        if (fin.isBefore(inicio)) {
            mensajeError.setText("La fecha de fin no puede ser anterior a la de inicio.");
            return;
        } else {
            mensajeError.setText("");
        }
        
        Map<String, Map<String, Integer>> stats = DatabaseManager.obtenerEstadisticasPorFecha(usuario.getNick(), inicio, fin);

        chart.getData().clear();

        XYChart.Series<String, Number> seriesAciertos = new XYChart.Series<>();
        seriesAciertos.setName("Aciertos");

        XYChart.Series<String, Number> seriesFallos = new XYChart.Series<>();
        seriesFallos.setName("Fallos");
        
        chart.setLegendSide(javafx.geometry.Side.TOP);
        
        ObservableList<String> categorias = javafx.collections.FXCollections.observableArrayList();
        
        int maxValor = 0;
        
        LocalDate date = inicio;
        while (!date.isAfter(fin)) {
            String fechaStr = date.toString();
            categorias.add(fechaStr);
            
            Map<String, Integer> res = stats.getOrDefault(fechaStr, new HashMap<>());
            int aciertos = res.getOrDefault("acierto", 0);
            int fallos = res.getOrDefault("fallo", 0);

            seriesAciertos.getData().add(new XYChart.Data<>(fechaStr, aciertos));
            seriesFallos.getData().add(new XYChart.Data<>(fechaStr, fallos));

            if (aciertos > maxValor) maxValor = aciertos;
            if (fallos > maxValor) maxValor = fallos;
        
            date = date.plusDays(1);
        }

        xAxis.setCategories(categorias);  // Asignas la lista de fechas al eje X

        chart.getData().addAll(seriesAciertos, seriesFallos);
    
        ObservableList<String> items = javafx.collections.FXCollections.observableArrayList();

        date = inicio;
        while (!date.isAfter(fin)) {
            String fechaStr = date.toString();
            Map<String, Integer> res = stats.getOrDefault(fechaStr, new HashMap<>());
            int aciertos = res.getOrDefault("acierto", 0);
            int fallos = res.getOrDefault("fallo", 0);
            String linea = "Fecha: " + fechaStr + " | Aciertos: " + aciertos + " | Fallos: " + fallos;
            items.add(linea);
            date = date.plusDays(1);
        }

        listView.setItems(items);
    }


    @FXML
    private void handleMostrar(ActionEvent event) {
        cargarDatosEstadisticas();
    }
}
