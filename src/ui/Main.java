package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.AlgorithmCYK;

public class Main extends Application{
	
	private AlgorithmGUI algorithmGUI;
	private AlgorithmCYK algorithmCYK;
	
	public Main(){
		algorithmCYK = new AlgorithmCYK();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		algorithmGUI = new AlgorithmGUI(algorithmCYK);
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
		
		fxmlLoader.setController(algorithmGUI);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Autómatas de Estados Finitos");
		primaryStage.setResizable(false);
		primaryStage.show();
		algorithmGUI.loadBanner();
	}

}
