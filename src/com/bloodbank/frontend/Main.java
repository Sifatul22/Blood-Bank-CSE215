package com.bloodbank.frontend;
	
import java.sql.SQLException;

import com.bloodbank.db_ops.DB_connect;
import com.bloodbank.frontend.controllers.Table_Data_Model;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application implements FxmlPackageNames {
	
    private ObservableList<Table_Data_Model> donorsData = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) {
		try {
			//FXMLLoader loader = new FXMLLoader();
			//AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource(name_fxml+"HomePage.fxml"));

			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(name_fxml+"Login.fxml"));
			
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource(name_fxml+"AddNewDonor.fxml"));
			
			//
			
//	        FXMLLoader loader = new FXMLLoader();
//	        loader.setLocation(Main.class.getResource(name_fxml+"DonorList.fxml"));
//	        AnchorPane root = (AnchorPane) loader.load();
//
//
//			DonorListController controller = loader.getController();
//	        controller.setMainApp(this);

			
			//
			
			
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource(name_fxml_css+"login.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Login/Register");

			
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream(name_fxml + "icon.png")));

			
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(new EventHandler <WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
				    try {
				    	//DB_connect.dbConn.close();
						Platform.exit();

					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println(e.getMessage());
					}
				    				    
					
				}
				
				
			});
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		
		launch(args);
		
		
	}
	
	public Main() {
		
		//donorsData.add(new Table_Data_Model("abc", "A+", "Dhaka"));
		//donorsData.add(new Table_Data_Model("def", "B+", "Savar"));

		
	}
	
	
    public ObservableList<Table_Data_Model> getDonorsData() {
        return donorsData;
    }

}
