package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.AlgorithmCYK;

public class AlgorithmGUI {
	
	public AlgorithmCYK algorithmCYK;
	
	@FXML
	private BorderPane mainPane;
	
	@FXML
	private Pane insertValues;
	
	@FXML
    private TextField contentState;

    @FXML
    private TextField state;
	
    double num = 0;
    
	public AlgorithmGUI(AlgorithmCYK algorithmCYK) {
		this.algorithmCYK = algorithmCYK;
	}
	
	@FXML
    public void newState(ActionEvent event) {
		TextField newState = new TextField();
		newState.setPrefHeight(state.getPrefHeight());
		newState.setPrefWidth(state.getPrefWidth());
		newState.setLayoutX(state.getLayoutX());
		newState.setLayoutY(num + 40);
		num = newState.getLayoutY();
		insertValues.getChildren().add(newState);
    }

	public void loadBanner() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("startProgram.fxml"));

		loader.setController(this);
		Parent load = loader.load();

		mainPane.getChildren().clear();
		mainPane.setTop(load);
		num = (int) state.getLayoutY();
	}

}
