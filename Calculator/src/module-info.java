/**
 * 
 */
/**
 * @author chmie
 *
 */
module calculator {
    requires transitive javafx.controls;
    requires javafx.fxml;
	requires jdk.jshell;
	requires transitive javafx.base;
    
	opens controllers;
	exports main;
	exports controllers;
	exports model;
}