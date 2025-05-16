/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        FXMLLoader loaderIniciarSesion = new  FXMLLoader(getClass().getResource("FXML_IniciarSesion.fxml"));
        Parent root = loaderIniciarSesion.load();
        
        // Obtienes la instancia del controlador asociado al FXML cargado
        FXML_IniciarSesionController controller = loaderIniciarSesion.getController();
        // Le pasas al controlador la referencia a la clase principal de la aplicación
        controller.setMainApp(this);
        
        Scene sceneIniciarSesion = new Scene(root);
        ventanaPrincipal.setScene(sceneIniciarSesion);
        ventanaPrincipal.setTitle("Menu principal");
        ventanaPrincipal.setMinWidth(650);
        ventanaPrincipal.setMinHeight(450);
        ventanaPrincipal.show();
    }
    
    public void startRegistrarUsuario() throws Exception {
        // Prueba (se podría lanzar todo esto en un metodo para llamar a estos menus) 
        FXMLLoader loaderRegistrarUsuario = new  FXMLLoader(getClass().getResource("FXML_RegistrarUsuario.fxml"));
        Parent root2 = loaderRegistrarUsuario.load();
        
        FXML_RegistrarUsuarioController controller = loaderRegistrarUsuario.getController();
        controller.setMainApp(this);
        
        Scene sceneRegistrarUsuario = new Scene(root2);
        ventanaPrincipal.setScene(sceneRegistrarUsuario);
        ventanaPrincipal.setTitle("Registrar usuario");
        ventanaPrincipal.setMinWidth(650);
        ventanaPrincipal.setMinHeight(450);
        ventanaPrincipal.show();
    }
    
    public void startMenuEstadisticas(Stage stage) throws Exception {
        FXMLLoader loaderMenuEstadisticas = new  FXMLLoader(getClass().getResource("FXML_MenuEstadisticas.fxml"));
        Parent root3 = loaderMenuEstadisticas.load();
        
        FXML_RegistrarUsuarioController controller = loaderMenuEstadisticas.getController();
        controller.setMainApp(this);
        
        Scene sceneMenuEstadisticas = new Scene(root3);
        ventanaPrincipal.setScene(sceneMenuEstadisticas);
        ventanaPrincipal.setTitle("Estadisticas Usuario");
        ventanaPrincipal.setMinWidth(650);
        ventanaPrincipal.setMinHeight(450);
        ventanaPrincipal.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }


    
}
