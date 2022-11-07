package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    
    @FXML
    private Label arrow;
    
    @FXML
    private ImageView background;

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
        Label newArrow = new Label();
        
        Font font = Font.font("System", FontWeight.EXTRA_BOLD, 25);
        
        newArrow.setStyle(arrow.getStyle());
        newArrow.setText(arrow.getText());
        newArrow.setFont(font);
        
        newState.setPrefHeight(state.getPrefHeight());
        newState.setPrefWidth(state.getPrefWidth());
        newState.setLayoutX(state.getLayoutX());
        newState.setLayoutY(positionY + 40);

        newContentState.setPrefHeight(contentState.getPrefHeight());
        newContentState.setPrefWidth(contentState.getPrefWidth());
        newContentState.setLayoutX(contentState.getLayoutX());
        newContentState.setLayoutY(positionY + 40);
        
        newArrow.setPrefHeight(arrow.getPrefHeight());
        newArrow.setPrefWidth(arrow.getPrefWidth());
        newArrow.setLayoutX(arrow.getLayoutX());
        newArrow.setLayoutY(positionY + 30);
        
        newState.setId(String.valueOf(cont));
        newContentState.setId(String.valueOf(cont));

        cont++;

        statesArray.add(newState);
        contentArray.add(newContentState);

        positionY += 40;

        if (newState.getLayoutY() >= insertValues.getHeight()) {
            insertValues.setPrefHeight(positionY + 40);
        }

        insertValues.getChildren().add(newState);
        insertValues.getChildren().add(newContentState);
        insertValues.getChildren().add(newArrow);
    }

	public void loadBanner() throws IOException {
		
		this.algorithmCYK = new AlgorithmCYK();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("startProgram.fxml"));

		loader.setController(this);
		Parent load = loader.load();

		mainPane.getChildren().clear();
		mainPane.setTop(load);
		
		Image back = new Image("/image/background.png");
		background.setImage(back);
		
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
		
		calculateCYK();
	}
	
	public void calculateCYK() throws IOException{
		boolean verify = algorithmCYK.calculateCYK();
		
		if(verify) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Algoritmo CYK");
			alert.setHeaderText("Excelente!");
			alert.setContentText("La cadena "+stringValue.getText()+" pertenece al lenguaje de la gramática");
			alert.show();
			
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Algoritmo CYK");
			alert.setHeaderText("Ups!");
			alert.setContentText("La cadena "+stringValue.getText()+" NO pertenece al lenguaje de la gramática");
			alert.showAndWait();
		}
	}

}
