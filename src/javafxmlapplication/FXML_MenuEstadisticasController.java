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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FXML_MenuEstadisticasController implements Initializable {

    private JavaFXMLApplication mainApp;
    private Usuario usuario;

    @FXML private CategoryAxis xAxis;
    @FXML private LineChart<String, Number> chart;
    @FXML private DatePicker fechaInicio;
    @FXML private DatePicker fechaFin;
    @FXML private Button volverB;
    @FXML private BorderPane rootPane;
    @FXML private Button mostrarB;
    @FXML private NumberAxis yAxis;
    @FXML private ScrollPane scrollPane;
    @FXML private ListView<String> listView;
    @FXML private Label mensajeError;
    @FXML private Label porcentajeLabel;
    @FXML private ProgressBar progressBar;
    @FXML private Circle progressCircle;

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

        ObservableList<String> categorias = javafx.collections.FXCollections.observableArrayList();
        int totalAciertos = 0, totalFallos = 0;

        LocalDate date = inicio;
        while (!date.isAfter(fin)) {
            String fechaStr = date.toString();
            categorias.add(fechaStr);

            Map<String, Integer> res = stats.getOrDefault(fechaStr, new HashMap<>());
            int aciertos = res.getOrDefault("acierto", 0);
            int fallos = res.getOrDefault("fallo", 0);

            totalAciertos += aciertos;
            totalFallos += fallos;

            seriesAciertos.getData().add(new XYChart.Data<>(fechaStr, aciertos));
            seriesFallos.getData().add(new XYChart.Data<>(fechaStr, fallos));

            date = date.plusDays(1);
        }

        xAxis.setCategories(categorias);
        chart.getData().addAll(seriesAciertos, seriesFallos);

        listView.setItems(generarResumen(stats, inicio, fin));
        actualizarPorcentaje(totalAciertos, totalFallos);
    }

    private void actualizarPorcentaje(int aciertos, int fallos) {
        int total = aciertos + fallos;
        double porcentaje = total == 0 ? 0 : (double) aciertos / total;
        int porcentajeInt = (int) Math.round(porcentaje * 100);

        porcentajeLabel.setText(porcentajeInt + "% de ejercicios completados");
        progressBar.setProgress(porcentaje);

        if (porcentaje == 1.0) {
            progressCircle.setStyle("-fx-fill: null; -fx-stroke: green; -fx-stroke-width: 35;");
        } else if (porcentaje == 0.0 && total > 0) {
            progressCircle.setStyle("-fx-fill: null; -fx-stroke: red; -fx-stroke-width: 35;");
        } else {
            progressCircle.setStyle("-fx-fill: null; -fx-stroke: orange; -fx-stroke-width: 35;");
        }
    }

    private ObservableList<String> generarResumen(Map<String, Map<String, Integer>> stats, LocalDate inicio, LocalDate fin) {
        ObservableList<String> items = javafx.collections.FXCollections.observableArrayList();
        LocalDate date = inicio;

        while (!date.isAfter(fin)) {
            String fechaStr = date.toString();
            Map<String, Integer> res = stats.getOrDefault(fechaStr, new HashMap<>());
            int aciertos = res.getOrDefault("acierto", 0);
            int fallos = res.getOrDefault("fallo", 0);
            items.add(fechaStr + " | Aciertos: " + aciertos + " | Fallos: " + fallos);
            date = date.plusDays(1);
        }

        return items;
    }

    @FXML
    private void handleMostrar(ActionEvent event) {
        cargarDatosEstadisticas();
    }
}
