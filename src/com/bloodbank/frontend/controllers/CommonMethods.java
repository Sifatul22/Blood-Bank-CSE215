package com.bloodbank.frontend.controllers;

import java.io.IOException;
import java.sql.SQLException;

import com.bloodbank.db_ops.DB_connect;
import com.bloodbank.frontend.FxmlPackageNames;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CommonMethods implements FxmlPackageNames {
	
	//DB_connect db = new DB_connect("jdbc:mysql://ashik72.me/java_blood_bank", "jbb01", "Qx4dVRduhbcUWK9Z");
	
	DB_connect db = new DB_connect("jdbc:mysql://"+db_host+"/"+db_name+"?autoReconnect=true", db_user, db_pass);

	public static int logged_in_user_id;
	//public static int logged_in_user_id = 2;
	public static Stage currentStage;

	protected void addNewStage(String title, String res, String css, Boolean resize) {
		
		Stage st = new Stage();
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(name_fxml+res));
			System.out.println(getClass().toString());
		} catch (IOException e) {
			//e.printStackTrace();
			root = null;
		}
		
		Scene scene = new Scene(root);
		
		System.out.println(css.length());
		
		if (css.length() > 0)
			scene.getStylesheets().add(name_fxml_css+css);
		
		st.setScene(scene);
		st.setResizable(resize);
		
		if (title.length() > 0)
			st.setTitle(title);
		
		st.show();

		st.setOnCloseRequest(event -> {
		    try {
		    	DB_connect.dbConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}
	
	protected void addNewStage(String title, String res, String css) {
	
		addNewStage(title, res, css, false);
	
	}
	
	protected void addNewStage(String res, String css) {
		
		addNewStage("", res, css, false);
	
	}
	
	protected void addNewStage(String res) {
		
		addNewStage("", res, "", false);
	
	}
	
	public void quit_platform_common() {
		
		Platform.exit();
	}
	
	public void quit_stage_common(Button el) {
		
		Stage stage = (Stage) el.getScene().getWindow();
	    stage.close();

	}
	
	protected <T> Object addNewStageController(String title, String res, String css, Boolean resize) {
		
	//protected <T extends CommonMethods> T addNewStageController(String title, String res, String css, Boolean resize, Class<T> type) {
		
		Stage st = new Stage();
		
		AnchorPane root;
		FXMLLoader loader;
		try {
			root = FXMLLoader.load(getClass().getResource(name_fxml+res));
			
	        loader = new FXMLLoader();
	        loader.setLocation(LoaderTheTable.class.getResource(name_fxml+res));
	        root = (AnchorPane) loader.load();


			
		} catch (IOException e) {
			//e.printStackTrace();
			root = null;
			loader = null;
		}
		
		T controller = loader.getController();

		Scene scene = new Scene(root);
		
		//System.out.println(css.length());
		
		if (css.length() > 0)
			scene.getStylesheets().add(name_fxml_css+css);
		
		st.setScene(scene);
		st.setResizable(resize);
		
		if (title.length() > 0)
			st.setTitle(title);
		
		st.show();
		
		st.setOnCloseRequest(event -> {
		    try {
		    	//DB_connect.dbConn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		

        //controller.setMainApp(this);
//        controller.setDataRole();
//        controller.setDataBloodGroup();
//        controller.setImageMsg();
        
	    //return type.cast(controller);
		return controller;
		
	}


}
