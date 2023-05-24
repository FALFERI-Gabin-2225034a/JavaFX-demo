package com.example.partie3.exercice11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MoyPopulationController implements Initializable {
    @FXML
    private static int dateID = 1;
    @FXML
    private Button addButton;
    @FXML
    private VBox inputContainer;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    private ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barChart.getXAxis().setLabel("Date");
        barChart.getYAxis().setLabel("Population");
        XYChart.Series<String, Number> series = new XYChart.Series<>(data);
        series.setName("Evolution of the number of whales");
        barChart.getData().add(series);
    }

    @FXML
    private void addPopulation() {
        int id = dateID++;
        String date = "Date " + id;
        Label label = new Label(date);

        VBox vBox = new VBox(label);
        vBox.setAlignment(Pos.CENTER);

        TextField textField = new TextField();
        textField.setPromptText("Population");
        textField.setId(String.valueOf(id));
        textField.setOnAction(event -> updatePopulation(event));

        HBox hBox = new HBox(vBox, textField);
        hBox.setSpacing(10);

        inputContainer.getChildren().add(hBox);
    }

    @FXML
    private void updatePopulation(ActionEvent event) {
        TextField target = (TextField) event.getSource();
        String date = "Date " + target.getId();
        String populationText = target.getText();
        try {
            int population = Integer.parseInt(populationText);
            int id = Integer.parseInt(target.getId()) - 1;
            if (data.isEmpty() || data.size() - 1 < id) {
                data.add(new XYChart.Data<>(date, population));
            }
            else
                data.get(id).setYValue(population);
        } catch (NumberFormatException e) {
            showErrorPopup("Erreur de format", "La population doit être un nombre entier.");
        }
    }
    private void showErrorPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
