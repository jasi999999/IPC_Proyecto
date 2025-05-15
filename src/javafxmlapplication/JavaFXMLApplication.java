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
    public void start(Stage stage) throws Exception {
        //======================================================================
        // 1- creación del grafo de escena a partir del fichero FXML
        FXMLLoader loaderIniciarSesion= new  FXMLLoader(getClass().getResource("FXML_IniciarSesion.fxml"));
        FXMLLoader loaderRegistrarUsuario= new  FXMLLoader(getClass().getResource("FXML_RegistrarUsuario.fxml"));
        Parent root = loaderIniciarSesion.load();
        Parent root2 = loaderRegistrarUsuario.load();
        //======================================================================
        // 2- creación de la escena con el nodo raiz del grafo de escena
        Scene scene = new Scene(root);
        Scene scene2 = new Scene(root2);
        
        //======================================================================
        // 3- asiganación de la escena al Stage que recibe el metodo 
        //     - configuracion del stage
        //     - se muestra el stage de manera no modal mediante el metodo show()
        stage.setScene(scene);
        stage.setTitle("PROJECT - IPC:");
        stage.setMinWidth(650);
        stage.setMinHeight(450);
        stage.show();
        
        // Prueba
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.setTitle("Registrar usuario");
        stage2.setMinWidth(650);
        stage2.setMinHeight(450);
        stage2.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }


    
}
