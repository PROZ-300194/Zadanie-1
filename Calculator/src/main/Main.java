package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/com/fxml/CalcScreen.fxml"));
		VBox vbox = loader.load();
		Scene scene = new Scene(vbox);
		scene.getStylesheets().add(getClass().getResource("/resources/css/style.css").toExternalForm());	//unneccesary
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Calculator");
		primaryStage.show();
		
	}
}