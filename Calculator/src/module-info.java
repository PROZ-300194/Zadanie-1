/**
 * 
 */
/**
 * @author chmie
 *
 */
module src {
	requires jdk.jshell;
	requires javafx.fxml;
	requires transitive javafx.base;
	requires transitive javafx.controls;

	opens controllers;

	exports main;
	exports controllers;
	exports model;
}