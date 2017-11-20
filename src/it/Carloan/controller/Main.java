package it.Carloan.controller;

import it.Carloan.controller.model.Agenzia;
import it.Carloan.database.Crud;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import it.Carloan.controller.model.Agenzia;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("view/Home.fxml"));
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("CARLOAN");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}