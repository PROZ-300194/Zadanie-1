package controllers;

import java.util.function.Function;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Model;

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

	public void initialize() {
		label.setText("0");
	}

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

	@FXML
	public void cButton() {
		label.setText("0");
		dotFlag = false;
		num1 = "0";
		op = "";
		newNumberFlag = true;
	}

	public void errorWindow(String errorMsg) {
		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
		errorAlert.setTitle("Error");
		errorAlert.setHeaderText(errorMsg);
		errorAlert.setContentText("Press OK to continue");
		errorAlert.showAndWait();
	}

}
