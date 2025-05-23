package javafxmlapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXMLApplication extends Application {

    private Stage ventanaPrincipal;
    // Medidas de VENTANA m´nimas y predeterminadas:
    private double anchoVentana = 620;
    private double altoVentana = 560;
    // Medidas de los PANE: 600, 520.
    // Los 20 extra de ancho: ventana
    // Los 40 extra de alto: menú + ventana
    
    @Override
    public void start(Stage stage) throws Exception {
        this.ventanaPrincipal = stage;
        
        // Inicializar con valores mínimos
        ventanaPrincipal.setMinWidth(anchoVentana);
        ventanaPrincipal.setMinHeight(altoVentana);
        
        // Listeners para guardar tamaño cuando cambia la ventana
        ventanaPrincipal.widthProperty().addListener((obs, viejoVal, nuevoVal) -> {
            if (nuevoVal.doubleValue() >= ventanaPrincipal.getMinWidth()) {
                anchoVentana = nuevoVal.doubleValue();
            }
        });
        ventanaPrincipal.heightProperty().addListener((obs, viejoVal, nuevoVal) -> {
            if (nuevoVal.doubleValue() >= ventanaPrincipal.getMinHeight()) {
                altoVentana = nuevoVal.doubleValue();
            }
        });
        
        startIniciarSesion();
    }

    public void startIniciarSesion() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_IniciarSesion.fxml"));
        Parent root = loader.load();
        FXML_IniciarSesionController controller = loader.getController();
        controller.setMainApp(this);
        ventanaPrincipal.setScene(new Scene(root));
        ventanaPrincipal.setTitle("Iniciar sesión");
        ventanaPrincipal.setMinWidth(620);
        ventanaPrincipal.setMinHeight(560);
        ventanaPrincipal.setWidth(Math.max(anchoVentana, 620));
        ventanaPrincipal.setHeight(Math.max(altoVentana, 560));
        ventanaPrincipal.show();
    }

    public void startRegistrarUsuario() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_RegistrarUsuario.fxml"));
        Parent root = loader.load();
        FXML_RegistrarUsuarioController controller = loader.getController();
        controller.setMainApp(this);
        ventanaPrincipal.setScene(new Scene(root));
        ventanaPrincipal.setTitle("Registrar usuario");
        ventanaPrincipal.setMinWidth(620);
        ventanaPrincipal.setMinHeight(560);
        ventanaPrincipal.setWidth(Math.max(anchoVentana, 620));
        ventanaPrincipal.setHeight(Math.max(altoVentana, 560));
        ventanaPrincipal.show();
    }

    public void startMenuUsuario() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_MenuUsuario.fxml"));
        Parent root = loader.load();
        FXML_MenuUsuarioController controller = loader.getController();
        controller.setMainApp(this);
        ventanaPrincipal.setScene(new Scene(root));
        ventanaPrincipal.setTitle("Menú Usuario");
        ventanaPrincipal.setMinWidth(620);
        ventanaPrincipal.setMinHeight(560);
        ventanaPrincipal.setWidth(Math.max(anchoVentana, 620));
        ventanaPrincipal.setHeight(Math.max(altoVentana, 560));
        ventanaPrincipal.show();
    }
    
    public void startMenuEstadisticas() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_MenuEstadisticas.fxml"));
        Parent root = loader.load();
        FXML_MenuEstadisticasController controller = loader.getController();
        // controller.setMainApp(this);
        ventanaPrincipal.setScene(new Scene(root));
        ventanaPrincipal.setTitle("Menú Estadísticas");
        ventanaPrincipal.setMinWidth(620);
        ventanaPrincipal.setMinHeight(560);
        ventanaPrincipal.setWidth(Math.max(anchoVentana, 620));
        ventanaPrincipal.setHeight(Math.max(altoVentana, 560));
        ventanaPrincipal.show();
    }
    
    public void startModificarPerfil() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ModificarPerfil.fxml"));
        Parent root = loader.load();
        FXML_ModificarPerfilController controller = loader.getController();
        controller.setMainApp(this);
        ventanaPrincipal.setScene(new Scene(root));
        ventanaPrincipal.setTitle("Modificar Perfil");
        ventanaPrincipal.setMinWidth(620);
        ventanaPrincipal.setMinHeight(560);
        ventanaPrincipal.setWidth(Math.max(anchoVentana, 620));
        ventanaPrincipal.setHeight(Math.max(altoVentana, 560));
        ventanaPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}