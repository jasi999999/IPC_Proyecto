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
    
    public void startMenuEstadisticas(Stage stage) throws Exception {
        FXMLLoader loaderMenuEstadisticas = new  FXMLLoader(getClass().getResource("FXML_MenuEstadisticas.fxml"));
        Parent root3 = loaderMenuEstadisticas.load();
        
        FXML_RegistrarUsuarioController controller = loaderMenuEstadisticas.getController();
        controller.setMainApp(this);
        
        Scene sceneMenuEstadisticas = new Scene(root3);
        ventanaPrincipal.setScene(sceneMenuEstadisticas);
        ventanaPrincipal.setTitle("Estadísticas Usuario");
        ventanaPrincipal.setMinWidth(650);
        ventanaPrincipal.setMinHeight(450);
        ventanaPrincipal.show();
    }
    
    public void startMenuProblema(Stage stage) throws Exception {
        FXMLLoader loaderMenuProblema = new  FXMLLoader(getClass().getResource("FXML_MenuProblema.fxml"));
        Parent root5 = loaderMenuProblema.load();
        
        FXML_MenuProblemaController controller = loaderMenuProblema.getController();
        // controller.setMainApp(this);
        
        Scene sceneMenuProblema = new Scene(root5);
        ventanaPrincipal.setScene(sceneMenuProblema);
        ventanaPrincipal.setTitle("Menú Problema");
        ventanaPrincipal.setMinWidth(650);
        ventanaPrincipal.setMinHeight(450);
        ventanaPrincipal.show();
    }
    
    public void startMenuUsuario(Stage stage) throws Exception {
        FXMLLoader loaderMenuUsuario = new  FXMLLoader(getClass().getResource("FXML_MenuUsuario.fxml"));
        Parent root5 = loaderMenuUsuario.load();
        
        FXML_MenuUsuarioController controller = loaderMenuUsuario.getController();
        // controller.setMainApp(this);
        
        Scene sceneMenuUsuario = new Scene(root5);
        ventanaPrincipal.setScene(sceneMenuUsuario);
        ventanaPrincipal.setTitle("Menú Usuario");
        ventanaPrincipal.setMinWidth(650);
        ventanaPrincipal.setMinHeight(450);
        ventanaPrincipal.show();
    }
    
    public void startMesaTrabajo(Stage stage) throws Exception {
        FXMLLoader loaderMesaTrabajo = new  FXMLLoader(getClass().getResource("FXML_MesaTrabajo.fxml"));
        Parent root5 = loaderMesaTrabajo.load();
        
        FXML_MesaTrabajoController controller = loaderMesaTrabajo.getController();
        // controller.setMainApp(this);
        
        Scene sceneMesaTrabajo = new Scene(root5);
        ventanaPrincipal.setScene(sceneMesaTrabajo);
        ventanaPrincipal.setTitle("Mesa Trabajo");
        ventanaPrincipal.setMinWidth(650);
        ventanaPrincipal.setMinHeight(450);
        ventanaPrincipal.show();
    }
    
    public void startModificarPerfil(Stage stage) throws Exception {
        FXMLLoader loaderModificarPerfil = new  FXMLLoader(getClass().getResource("FXML_ModificarPerfil.fxml"));
        Parent root4 = loaderModificarPerfil.load();
        
        FXML_ModificarPerfilController controller = loaderModificarPerfil.getController();
        // controller.setMainApp(this);
        
        Scene sceneModificarPerfil = new Scene(root4);
        ventanaPrincipal.setScene(sceneModificarPerfil);
        ventanaPrincipal.setTitle("Modificar Perfil");
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }


    
}
