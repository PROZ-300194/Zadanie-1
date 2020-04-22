module Calculator {
	requires jdk.jshell;
	requires javafx.fxml;
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires javafx.graphics;
	
	opens controllers;

	exports main;
	exports controllers;
	exports model;
}