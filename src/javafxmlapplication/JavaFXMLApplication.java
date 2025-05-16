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
    
    @Override
    public void start(Stage ventanaPrincipal) throws Exception {
        //======================================================================
        // 1- creación del grafo de escena a partir del fichero FXML
        FXMLLoader loaderIniciarSesion = new  FXMLLoader(getClass().getResource("FXML_IniciarSesion.fxml"));
        Parent root = loaderIniciarSesion.load();
        
        //======================================================================
        // 2- creación de la escena con el nodo raiz del grafo de escena
        Scene sceneIniciarSesion = new Scene(root);
        
        //======================================================================
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del ventanaPrincipal
        //     - se muestra el ventanaPrincipal de manera no modal mediante el metodo show()
        ventanaPrincipal.setScene(sceneIniciarSesion);
        ventanaPrincipal.setTitle("PROJECT - IPC:");
        ventanaPrincipal.setMinWidth(650);
        ventanaPrincipal.setMinHeight(450);
        ventanaPrincipal.show();
        
        startIniciarSesion();
        startMenuEstadisticas();
    }
    
    private void startIniciarSesion() throws Exception {
        // Prueba (se podría lanzar todo esto en un metodo para llamar a estos menus) 
        FXMLLoader loaderRegistrarUsuario = new  FXMLLoader(getClass().getResource("FXML_RegistrarUsuario.fxml"));
        Parent root2 = loaderRegistrarUsuario.load();
        Scene sceneRegistrarUsuario = new Scene(root2);
        
        Stage stage2 = new Stage();
        stage2.setScene(sceneRegistrarUsuario);
        stage2.setTitle("Registrar usuario");
        stage2.setMinWidth(650);
        stage2.setMinHeight(450);
        stage2.show();
    }
    
    private void startMenuEstadisticas() throws Exception {
        // Prueba (se podría lanzar todo esto en un metodo para llamar a estos menus) 
        FXMLLoader loaderMenuEstadisticas = new  FXMLLoader(getClass().getResource("FXML_MenuEstadisticas.fxml"));
        Parent root3 = loaderMenuEstadisticas.load();
        Scene sceneMenuEstadisticas = new Scene(root3);
        
        Stage stage3 = new Stage();
        stage3.setScene(sceneMenuEstadisticas);
        stage3.setTitle("Estadisticas Usuario");
        stage3.setMinWidth(650);
        stage3.setMinHeight(450);
        stage3.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }


    
}
