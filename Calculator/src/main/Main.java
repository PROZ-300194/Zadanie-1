package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Klasa main rozpoczynaj¹ca dzia³anie programu
 * @author Daniel_Chmielewiec
 *
 */
public class Main extends Application {

	/**
	 * Metoda rozpoczynaj¹ca dzia³anie programu
	 * @param args Argumenty podane wraz z rozpoczêciem programu
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Metoda wyœwietlaj¹ca okienko kalkulatora i ustawiaj¹ca jego podstawowe parametry
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/resources/fxml/CalcScreen.fxml"));
			VBox vbox = loader.load();
			Scene scene = new Scene(vbox);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Calculator");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}