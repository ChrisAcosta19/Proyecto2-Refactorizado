/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo8.proyectofifa;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author laque
 */
public class Principal extends Application{
    public static Scene scene;
    public static String pathImg = "src/main/resources/image/";
    
    @Override
    public void start(Stage s)throws IOException{
        FXMLLoader fxmlloader = new FXMLLoader(Principal.class.getResource("ventana.fxml"));
        Parent root = fxmlloader.load();
        scene = new Scene(root);
        s.setScene(scene);
        s.show();
        s.setTitle("Copa mundial de la FIFA Brasil 2014");
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }
}