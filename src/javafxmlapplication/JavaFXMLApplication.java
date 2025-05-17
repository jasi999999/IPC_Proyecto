package javafxmlapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXMLApplication extends Application {

    private Stage ventanaPrincipal;

    @Override
    public void start(Stage stage) throws Exception {
        this.ventanaPrincipal = stage;
        startIniciarSesion();
    }

    public void startIniciarSesion() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_IniciarSesion.fxml"));
        Parent root = loader.load();
        FXML_IniciarSesionController controller = loader.getController();
        controller.setMainApp(this);
        ventanaPrincipal.setScene(new Scene(root));
        ventanaPrincipal.setTitle("Iniciar sesión");
        ventanaPrincipal.setMinWidth(650);
        ventanaPrincipal.setMinHeight(520);
        ventanaPrincipal.show();
    }

    public void startRegistrarUsuario() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_RegistrarUsuario.fxml"));
        Parent root = loader.load();
        FXML_RegistrarUsuarioController controller = loader.getController();
        controller.setMainApp(this);
        ventanaPrincipal.setScene(new Scene(root));
        ventanaPrincipal.setTitle("Registrar usuario");
        ventanaPrincipal.setMinWidth(600);
        ventanaPrincipal.setMinHeight(520);
        ventanaPrincipal.show();
    }

    public void startMenuUsuario() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_MenuUsuario.fxml"));
        Parent root = loader.load();
        FXML_MenuUsuarioController controller = loader.getController();
        ventanaPrincipal.setScene(new Scene(root));
        ventanaPrincipal.setTitle("Menú Usuario");
        ventanaPrincipal.setMinWidth(650);
        ventanaPrincipal.setMinHeight(450);
        ventanaPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
