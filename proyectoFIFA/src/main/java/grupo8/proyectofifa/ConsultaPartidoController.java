/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package grupo8.proyectofifa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author laque
 */
public class ConsultaPartidoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labeltitulo.setId("labeltitulo");
        cbfase.getItems().addAll("Grupos", "Ronda de 16", "Cuartos de Final", "Semifinal","Final");
    }    
    
    @FXML
    private VBox root;
    
    @FXML
    private HBox hbox0;
    
    @FXML
    private ComboBox cbfase;
    
    @FXML
    private Label labeltitulo;
    
    @FXML
    public void faseEscogida(ActionEvent e){
        hbox0.getChildren().clear();
        root.getChildren().clear();
        root.getChildren().addAll(labeltitulo);
        
        VBox vbox0 = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        int i = 0;
        ArrayList<String> equiposSR = new ArrayList<>();
        ArrayList<String> listafase = new ArrayList<>();
        ArrayList<String> equipos1 = new ArrayList<>();
        ArrayList<String> equipos2 = new ArrayList<>();
        
        if (cbfase.getValue() != "Grupos"){
            String linea;
            String[] contenido;
            try (BufferedReader lector = new BufferedReader(new FileReader(Principal.pathArc+"WorldCupMatchesBrasil2014.csv"))) {
                lector.readLine();
                while((linea = lector.readLine())!= null){
                    contenido = linea.split("\\|");
                    listafase.add(contenido[2]);
                    equipos1.add(contenido[5]);
                    equipos2.add(contenido[8]);
                }    
            }catch(IOException ioe){
                System.out.println("no se pudo leer");
            }
            hbox1.getChildren().addAll(new Label("Fase: "), cbfase);
            hbox1.setSpacing(10);
            hbox1.setAlignment(Pos.CENTER);
            hbox1.setPadding(new Insets(20, 10, 20, 10));
            vbox0.getChildren().add(hbox1);
            vbox0.getChildren().addAll(new Label("Los equipos que llegaron a esta fase fueron:"));
            vbox0.setAlignment(Pos.CENTER);
            vbox0.setSpacing(10);
        }
        
        if (cbfase.getValue() == "Grupos"){
            ComboBox cbgrupo = new ComboBox();
            cbgrupo.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H");
            
            ComboBox cbequipo1 = new ComboBox();
            ComboBox cbequipo2 = new ComboBox();
            cbgrupo.setOnAction(g->cargarCB((String) cbgrupo.getValue(), cbequipo1, cbequipo2));
            
            hbox1.getChildren().addAll(new Label("Fase: "), cbfase, new Label("        "
                    + "        "), new Label("Grupo: "), cbgrupo);
            hbox1.setSpacing(10);
            hbox1.setAlignment(Pos.CENTER);
            hbox1.setPadding(new Insets(20, 10, 20, 10));
            vbox0.getChildren().add(hbox1);
            
            hbox2.getChildren().addAll(new Label("Equipo 1: "), cbequipo1, new Label("               "
                    + "vs               ") ,new Label("Equipo 2: "), cbequipo2);
            hbox2.setSpacing(10);
            hbox2.setAlignment(Pos.CENTER);
            hbox2.setPadding(new Insets(0, 10, 20, 10));
            
            vbox0.getChildren().add(hbox2);
            Button btnconslutar = new Button("Consultar");
            Label resultadoPartido = new Label("");
            VBox vbox1 = new VBox();
            HBox hboxmini = new HBox();
            VBox vboxmini = new VBox();
            btnconslutar.setOnMouseClicked(new EventHandler<MouseEvent>(){
                
                @Override
                public void handle(MouseEvent e){
                    eventoConsultar((String) cbgrupo.getValue(), (String) cbequipo1.getValue(), (String) cbequipo2.getValue(), vbox1, hboxmini, vboxmini, resultadoPartido, vbox0);
              
                }
          
            });
            vbox0.getChildren().addAll(btnconslutar);
            vbox0.setAlignment(Pos.CENTER);
            vbox0.getChildren().addAll(resultadoPartido);
            
        }else if(cbfase.getValue() == "Ronda de 16"){
            for(String fase: listafase){
                completarLista(fase, "Round of 16", equiposSR, equipos1, equipos2, i);
                i += 1;
            }
            Collections.sort(equiposSR);
            for (int j = 0; j <equiposSR.size(); j++){
                if(j != equiposSR.size()-1){
                    hbox2.getChildren().addAll(new Label(equiposSR.get(j)+ ", "));
                }else{
                    hbox2.getChildren().addAll(new Label(equiposSR.get(j)));
                }
            }

            hbox2.setAlignment(Pos.CENTER);
            vbox0.getChildren().add(hbox2);
            
        }else if(cbfase.getValue() == "Cuartos de Final"){
            for(String fase: listafase){
                completarLista(fase, "Quarter-finals", equiposSR, equipos1, equipos2, i);
                i += 1;
            }
            Collections.sort(equiposSR);
            for (int j = 0; j <equiposSR.size(); j++){
                if(j != equiposSR.size()-1){
                    hbox2.getChildren().addAll(new Label(equiposSR.get(j)+ ", "));
                }else{
                    hbox2.getChildren().addAll(new Label(equiposSR.get(j)));
                }
            }

            hbox2.setAlignment(Pos.CENTER);
            vbox0.getChildren().add(hbox2);
            
        }else if(cbfase.getValue() == "Semifinal"){
            for(String fase: listafase){
                completarLista(fase, "Semi-finals", equiposSR, equipos1, equipos2, i);
                i += 1;
            }
            Collections.sort(equiposSR);
            for (int j = 0; j <equiposSR.size(); j++){
                if(j != equiposSR.size()-1){
                    hbox2.getChildren().addAll(new Label(equiposSR.get(j)+ ", "));
                }else{
                    hbox2.getChildren().addAll(new Label(equiposSR.get(j)));
                }
            }

            hbox2.setAlignment(Pos.CENTER);
            vbox0.getChildren().add(hbox2);
            
        }else if(cbfase.getValue() == "Final"){
            for(String fase: listafase){
                completarLista(fase, "Final", equiposSR, equipos1, equipos2, i);
                i += 1;
            }
            Collections.sort(equiposSR);
            for (int j = 0; j <equiposSR.size(); j++){
                if(j != equiposSR.size()-1){
                    hbox2.getChildren().addAll(new Label(equiposSR.get(j)+ ", "));
                }else{
                    hbox2.getChildren().addAll(new Label(equiposSR.get(j)));
                }
            }

            hbox2.setAlignment(Pos.CENTER);
            vbox0.getChildren().add(hbox2);
            
        }
        
        root.getChildren().add(vbox0);
    }
    
    public void cargarCB(String valorSelecionado, ComboBox cbequipo1, ComboBox cbequipo2){
        switch(valorSelecionado){
            case "A":
                cargarEquipos("Group A", cbequipo1, 2, 5);
                cargarEquipos("Group A", cbequipo2, 2, 8);
                break;
            case "B":
                cargarEquipos("Group B", cbequipo1, 2, 5);
                cargarEquipos("Group B", cbequipo2, 2, 8);
                break;
            case "C":
                cargarEquipos("Group C", cbequipo1, 2, 5);
                cargarEquipos("Group C", cbequipo2, 2, 8);
                break;
            case "D":
                cargarEquipos("Group D", cbequipo1, 2, 5);
                cargarEquipos("Group D", cbequipo2, 2, 8);
                break;
            case "E":
                cargarEquipos("Group E", cbequipo1, 2, 5);
                cargarEquipos("Group E", cbequipo2, 2, 8);
                break;
            case "F":
                cargarEquipos("Group F", cbequipo1, 2, 5);
                cargarEquipos("Group F", cbequipo2, 2, 8);
                break;
            case "G":
                cargarEquipos("Group G", cbequipo1, 2, 5);
                cargarEquipos("Group G", cbequipo2, 2, 8);
                break;
            case "H":
                cargarEquipos("Group H", cbequipo1, 2, 5);
                cargarEquipos("Group H", cbequipo2, 2, 8);
                break;
        }
        
    }
    
    public void cargarEquipos(String valorSelecionado, ComboBox cbequipo, int indice1, int indice2){
        cbequipo.getItems().clear();
        String linea;
        String[] contenido;
        ArrayList<String> sinRepetir = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(Principal.pathArc+"WorldCupMatchesBrasil2014.csv"))) {
            lector.readLine();
            while((linea = lector.readLine())!= null){
                contenido = linea.split("\\|");
                if(valorSelecionado.equals(contenido[indice1]) && !sinRepetir.contains(contenido[indice2])){
                    sinRepetir.add(contenido[indice2]);
                }
            }    
        }catch(IOException ioe){
            System.out.println("no se pudo leer");
        }
        Collections.sort(sinRepetir);
        for(String elemento:sinRepetir){
            cbequipo.getItems().addAll(elemento);
        }
    }
    
    public void completarLista(String fase, String faseEscogida, ArrayList<String> equiposSR, ArrayList<String> equipos1, ArrayList<String> equipos2, int i){
        boolean condicion1;
        boolean condicion2;
        
        condicion1 = !equiposSR.contains(equipos1.get(i));
        condicion2 = !equiposSR.contains(equipos2.get(i));
        if(fase.equals(faseEscogida) && condicion1){
            equiposSR.add(equipos1.get(i));
        }
        
        if(fase.equals(faseEscogida) && condicion2){
            equiposSR.add(equipos2.get(i));
        }
    }
    
    public void eventoConsultar(String grupo, String cbequipo1, String cbequipo2, VBox vbox1, HBox hboxmini, VBox vboxmini, Label resultadoPartido, VBox vbox0){
        root.getChildren().clear();
        root.getChildren().addAll(labeltitulo);
        root.getChildren().add(vbox0);
        vbox1.getChildren().clear();
        hboxmini.getChildren().clear();
        vboxmini.getChildren().clear();
        resultadoPartido.setText("");
        if(cbequipo1 == null || cbequipo2==null){
            resultadoPartido.setText("Es necesario escoger una opción en equipo1 y equipo2 para cargar los resultados de un partido");
        }else{
            if(cbequipo1.equals(cbequipo2)){
                resultadoPartido.setText("Para cargar los resultados los dos equipos tienen que ser diferentes");
            }else{
                
                String linea;
                String[] contenido;
                Label dia = new Label();
                try (BufferedReader lector = new BufferedReader(new FileReader(Principal.pathArc+"WorldCupMatchesBrasil2014.csv"))) {
                lector.readLine();
                while((linea = lector.readLine())!= null){
                    contenido = linea.split("\\|");
                    if(contenido[2].equals("Group "+grupo) && cbequipo1.equals(contenido[5]) && cbequipo2.equals(contenido[8])){
                        vboxmini.getChildren().addAll(new Label(contenido[1]), new Label(contenido[3]), new Label(contenido[4]));
                        hboxmini.getChildren().addAll(vboxmini, new Label(cbequipo1), new Label("Final del partido: " + contenido[6] + " - " + contenido[7]), new Label(cbequipo2));
                        hboxmini.setSpacing(10);
                        hboxmini.setAlignment(Pos.CENTER);
                        dia.setText(diaPartido(contenido[1].split(" ")[0]));
                    }
                    if(contenido[2].equals("Group "+grupo) && cbequipo1.equals(contenido[8]) && cbequipo2.equals(contenido[5])){
                        vboxmini.getChildren().addAll(new Label(contenido[1]), new Label(contenido[3]), new Label(contenido[4]));
                        hboxmini.getChildren().addAll(vboxmini, new Label(cbequipo1), new Label("Final del partido: " + contenido[6] + " - " + contenido[7]), new Label(cbequipo2));
                        hboxmini.setSpacing(10);
                        hboxmini.setAlignment(Pos.CENTER);
                        dia.setText(diaPartido(contenido[1].split(" ")[0]));
                    }
                }    
                }catch(IOException ioe){
                    System.out.println("no se pudo leer");
                }
                
                vboxmini.getChildren().addAll();
                
                // Los botones para abrir con la ventana Exportar resultados y ver detalleestan aqui:--------------------------------
                Button btnResultadoGrupo = new Button();
                btnResultadoGrupo.setText("Exportar Resultados del grupo.");
                Button btnDetalle = new Button();
                btnDetalle.setText("Ver Detalle del grupo");
                //----------------------------------------------------------------------------------------------------------------------
                vbox1.getChildren().addAll(new Label("Resultado del partido"), dia, hboxmini, btnResultadoGrupo, btnDetalle);
                vbox1.setSpacing(10);
                vbox1.setAlignment(Pos.CENTER);
                root.getChildren().add(vbox1);
            }
        }
    }
    
    public String diaPartido(String numDia){
        switch (numDia) {
            case "16":
            case "23":
                return "Lunes " + numDia + " de junio";
            case "17":
            case "24":
                return "Martes " + numDia + " de junio";
            case "18":
            case "25":
                return "Miercoles " + numDia + " de junio";
            case "12":
            case "19":
            case "26":
                return "Jueves " + numDia + " de junio";
            case "13":
            case "20":
                return "Viernes " + numDia + " de junio";
            case "14":
            case "21":
                return "Sabado " + numDia + " de junio";
            case "15":
            case "22":
                return "Domingo " + numDia + " de junio";
            default:
                break;
        }
        
        return "";
    }
}
