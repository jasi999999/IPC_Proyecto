/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Problem;
import model.NavDAOException;
import model.Navigation;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class FXML_MenuElegirProblemaController implements Initializable {
    
    private List<Problem> problemas;
    private JavaFXMLApplication mainApp;
    private Usuario usuario;
    
    @FXML
    private Button seleccionarB;
    @FXML
    private Button atrasB;
    @FXML
    private ListView<String> problemasListView;
    
    public void setMainApp(JavaFXMLApplication mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            problemas = Navigation.getInstance().getProblems();
            ObservableList<String> items = FXCollections.observableArrayList();

            for (int i = 0; i < problemas.size(); i++) {
                items.add("Problema #" + (i + 1));
            }

            problemasListView.setItems(items);

            problemasListView.getSelectionModel().selectedIndexProperty().addListener(
                (obs, oldVal, newVal) -> {
                    int index = newVal.intValue();
                    if (index >= 0 && index < problemas.size()) {
                        System.out.println("Seleccionado: " + problemas.get(index).getText());
                    }
                }
            );

        } catch (NavDAOException e) {
            System.err.println("Error cargando los problemas: " + e.getMessage());
            e.printStackTrace();
        }
    }    

    @FXML
    private void handleSeleccionar(ActionEvent event) {
        int index = problemasListView.getSelectionModel().getSelectedIndex();

        if (index >= 0 && index < problemas.size()) {
            try {
                mainApp.startMenuProblema(usuario, index);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ningún problema seleccionado.");
        }
    }

    @FXML
    private void handleAtras(ActionEvent event) {
        try {
            mainApp.startMenuUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setIndexProblema(int index) {
        if (problemasListView != null && index >= 0 && index < problemasListView.getItems().size()) {
            problemasListView.getSelectionModel().select(index);
            problemasListView.scrollTo(index);
        }
    }
}
