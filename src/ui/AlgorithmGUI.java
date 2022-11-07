package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.AlgorithmCYK;

public class AlgorithmGUI {
	
	public AlgorithmCYK algorithmCYK;
	
	@FXML
	private BorderPane mainPane;
	
	@FXML
    private AnchorPane insertValues;
	
	@FXML
    private TextField contentState;

    @FXML
    private TextField state;
	
    private double positionY = 0;
    
    @FXML
    private Button addState;
    
    @FXML
    private TextField stringValue;
	
    private int cont = 1;
    
    private ArrayList<TextField> statesArray = new ArrayList<TextField>();
    private ArrayList<TextField> contentArray = new ArrayList<TextField>();
    
	public AlgorithmGUI(AlgorithmCYK algorithmCYK) {
		this.algorithmCYK = algorithmCYK;
	}
	
	@FXML
    public void newState(ActionEvent event) {
		
		TextField newState = new TextField();
		TextField newContentState = new TextField();
		
		newState.setPrefHeight(state.getPrefHeight());
		newState.setPrefWidth(state.getPrefWidth());
		newState.setLayoutX(state.getLayoutX());
		newState.setLayoutY(positionY + 40);
		
		newContentState.setPrefHeight(contentState.getPrefHeight());
		newContentState.setPrefWidth(contentState.getPrefWidth());
		newContentState.setLayoutX(contentState.getLayoutX());
		newContentState.setLayoutY(positionY + 40);
		
		newState.setId(String.valueOf(cont));
		newContentState.setId(String.valueOf(cont));
		
		cont++;
		
		statesArray.add(newState);
		contentArray.add(newContentState);
		
		positionY += 40;
		
		if (positionY >= insertValues.getHeight()) {
			insertValues.setPrefHeight(positionY + 40);
		}
		
		insertValues.getChildren().add(newState);
    }

	public void loadBanner() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("startProgram.fxml"));

		loader.setController(this);
		Parent load = loader.load();

		mainPane.getChildren().clear();
		mainPane.setTop(load);
		
		state.setId(String.valueOf(cont));
		contentState.setId(String.valueOf(cont));
		cont++;
		
		statesArray.add(state);
		contentArray.add(contentState);
		
		positionY = state.getLayoutY();
		
	}
	
	@FXML	
	public void addParameters(ActionEvent event) throws IOException{
		
		algorithmCYK.addString(stringValue.getText());
		
		for (int i = 0; i < statesArray.size(); i++) {
			algorithmCYK.addStates(statesArray.get(i).getText(), algorithmCYK.splitProductions(contentArray.get(i).getText()));
		}
	}
	
	public void calculateCYK() throws IOException{
		
	}

}
