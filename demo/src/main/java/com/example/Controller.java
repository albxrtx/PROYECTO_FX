package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.ObjectMapper;

public class Controller implements Initializable {

    @FXML
    private TextField tf_name;

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<Game> tableView;

    @FXML
    private TableColumn<Game, Integer> columnaPosicion;

    @FXML
    private TableColumn<Game, String> columnaNombre;

    @FXML
    private TableColumn<Game, Integer> columnaPuntuacion;

    private ObservableList<Game> listaGame;

    private Stage mainWindow;

    public void setMainWindow(Stage mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableColumns();
        fetchGameData();
        
    }
    public void fetchGameData(){
        String apiUrl = "https://magicloops.dev/api/loop/471f635b-0b4c-44e5-b811-a2f823baa79f/run?input=I+love+Magic+Loops%21"; 
        
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) { 
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                 
                reader.close();
                System.out.println(response.toString());
                addGamesToTable(response.toString());
            } else {
                System.out.println("Error en la conexión: " + responseCode);
            }
            
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void initializeTableColumns() {
        listaGame = FXCollections.observableArrayList();
    
        // Configurar las columnas
        columnaPosicion.setCellValueFactory(new PropertyValueFactory<>("position"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnaPuntuacion.setCellValueFactory(new PropertyValueFactory<>("score"));
    
        // Asociar la lista con la tabla
        tableView.setItems(listaGame);
    }
    public void addGamesToTable(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
        
            List<Game> temporalList = new ArrayList<>();

            // Recorrer el JSONArray y convertir cada objeto JSON en un Game
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int position = jsonObject.getInt("position");
                String name = jsonObject.getString("name");
                int score = jsonObject.getInt("score"); 
            
                Game temporalGame = new Game(position, name, score);
                temporalList.add(temporalGame);
            }

        for (Game game : temporalList) {
            listaGame.add(game);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}