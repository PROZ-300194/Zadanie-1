package controllers;

import java.util.function.Function;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Model;

/**
 * Kontroler odpowiedzialny za odpowiednie reakcje i kierowanie do innych
 * metod/klas po naciœnieciu danego przycisku
 * 
 * @author Daniel_Chmielewiec
 *
 */
public class Controller {

	private final int MAX_LENGTH = 11;
	private final static long MAX_VALUE = 9999999;
	private boolean dotFlag = false;
	private String op = "";
	private String num1 = "";
	private boolean newNumberFlag = false;
	private boolean doubleOpFlag = false;

	@FXML
	private Label label;

	/**
	 * Metoda inicjalizuj¹ca - ustawia pocz¹tkow¹ wartoœæ "0" na wyœwietlaczu
	 * kalkulatora
	 */
	public void initialize() {
		label.setText("0");
	}

	/**
	 * Metoda opdowiedzialna za dzia³anie po wciœnieciu przycisków liczbowych i "."
	 * 
	 * @param e Zawiera informacje o nasta³ym "wydarzeniu" (wciœnieciu przycisku)
	 */
	@FXML
	public void numButton(ActionEvent e) {
		String buttonText = ((Button) e.getSource()).getText();

		if (newNumberFlag == true) {
			newNumberFlag = false;
			label.setText("0");
		}

		String value = label.getText();

		if (buttonText.equals("."))
			if (dotFlag == true)
				return;
			else
				dotFlag = true;

		if (value.length() == MAX_LENGTH)
			return;
		else if (value.equals("0") && !buttonText.equals("."))
			value = buttonText;
		else {
			value = value + buttonText;
		}
		doubleOpFlag = false;
		label.setText(value);
	}

	/**
	 * Metoda odpowiedzialna za dzia³anie po wciœnieciu przycisków operatorów
	 * dwuargumentowych
	 * 
	 * @param e Zawiera informacje o nasta³ym "wydarzeniu" (wciœnieciu przycisku)
	 * @exception IllegalArgumentException Zg³aszany gdy wynik przekroczy maksymaln¹
	 *                                     dozwolon¹ wartoœæ
	 */
	@FXML
	public void opButton2Arg(ActionEvent e) {
		Function<ActionEvent, String> getOp = x -> ((Button) x.getSource()).getText();
		if (op.equals("") && getOp.apply(e).equals("=") || doubleOpFlag) {
			if (doubleOpFlag && !getOp.apply(e).equals("="))
				op = getOp.apply(e);
			return;
		} else if (op.equals("")) {
			op = getOp.apply(e);
			num1 = label.getText();
			newNumberFlag = true;
			doubleOpFlag = true;
			dotFlag = false;
		} else {
			try {
				double result = Model.calculate(num1, label.getText(), op);
				if (result > MAX_VALUE)
					throw new IllegalArgumentException("Too big number!");
				num1 = Model.makeItSuitable(Double.toString(result));
				newNumberFlag = true;
				dotFlag = false;
				doubleOpFlag = true;
				label.setText(Model.makeItSuitable(Double.toString(result)));
				if (getOp.apply(e).equals("="))
					op = "";
				else
					op = getOp.apply(e);
			} catch (IllegalArgumentException exception) {
				label.setText("Error!");
				errorWindow(exception.getMessage());
				cButton();
			}
		}
	}

	/**
	 * Metoda odpowiedzialna za dzia³anie po wciœnieciu przycisków operatorów
	 * jednoargumentowych
	 * 
	 * @param e Zawiera informacje o nasta³ym "wydarzeniu" (wciœnieciu przycisku)
	 */
	@FXML
	public void opButton1Arg(ActionEvent e) {
		Function<ActionEvent, String> getId = x -> ((Button) x.getSource()).getId();
		if (label.getText().equals("") || label.getText().equals("Error!"))
			return;
		else {
			op = getId.apply(e);
			try {
				double result = Model.calculate(label.getText(), op);
				num1 = Model.makeItSuitable(Double.toString(result));
				label.setText(num1);
				newNumberFlag = true;
				dotFlag = false;
				op = "";
			} catch (IllegalArgumentException exception) {
				label.setText("Error!");
				errorWindow(exception.getMessage());
				cButton();
			}
		}
	}

	/**
	 * Metoda odpowiadaj¹ca za wciœniecia przycisku "C"
	 */
	@FXML
	public void cButton() {
		label.setText("0");
		dotFlag = false;
		num1 = "0";
		op = "";
		newNumberFlag = true;
	}

	/**
	 * Metoda wyœwietlaj¹ca alert "Error"
	 * 
	 * @param errorMsg Wiadomoœæ przekazana po wyst¹pieniu b³edu wyœwietlana
	 *                 nastêpnie w alercie
	 */
	public static void errorWindow(String errorMsg) {
		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
		errorAlert.setTitle("Error");
		errorAlert.setHeaderText(errorMsg);
		errorAlert.setContentText("Press OK to continue");
		errorAlert.showAndWait();
	}

}
