package com.bloodbank.frontend.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bloodbank.db_ops.DB_connect;
import com.bloodbank.db_ops.Donors_Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoaderTheTable extends CommonMethods {

    private ObservableList<Table_Data_Model> donorsData = FXCollections.observableArrayList();

	public static void addNewStageViewProfile(String title, String res, String css, Boolean resize, int uid) {

		CommonMethods insCommonMethods = new CommonMethods();
		
		DonarProfileController myController = (DonarProfileController) insCommonMethods.addNewStageController(title, res, css, resize);
		
		myController.LoadDataToView(uid);
	}
	
	public static void addNewStageUpdateProfile(String title, String res, String css, Boolean resize, int uid) {

		CommonMethods insCommonMethods = new CommonMethods();
		
		DonorUpdateController myController = (DonorUpdateController) insCommonMethods.addNewStageController(title, res, css, resize);
		
		myController.LoadDataToView(uid);
		myController.setImageMsg();

	}
	
	public void addNewStageSearch(String title, String res, String css, Boolean resize) {

		CommonMethods insCommonMethods = new CommonMethods();
		
		DonorSearchController myController = (DonorSearchController) insCommonMethods.addNewStageController(title, res, css, resize);
		
		myController.setMainApp(getDonorsData());

	}
    
	protected void addNewStageTable(String title, String res, String css, Boolean resize) {
		
		Stage st = new Stage();
		
		AnchorPane root;
		try {
			root = FXMLLoader.load(getClass().getResource(name_fxml+res));
			
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(LoaderTheTable.class.getResource(name_fxml+res));
	        root = (AnchorPane) loader.load();


			DonorListController controller = loader.getController();
	        controller.setMainApp(this);

			
			
			
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
	
	public LoaderTheTable() {				
		
		//this.addItems();
		
	}
	
	private ObservableList<Table_Data_Model> addItems() {
		
		ArrayList<Donors_Data> donors = db.getDonorData();

		for (Donors_Data donor :  donors) {
			
			donorsData.add(new Table_Data_Model(donor.getName(), donor.getBloodGroup(), donor.getArea(), (Integer.toString(donor.getID())), donor.getContactNo()));
			try {
				//DB_connect.dbConn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
        return donorsData;

	}
 	
	
    public ObservableList<Table_Data_Model> getDonorsData() {
    	
    	donorsData.clear();
    	
    	donorsData = this.addItems();
    	
        return donorsData;
    }
    
    
	protected void addNewStageDonorAdd(String title, String res, String css, Boolean resize) {
		
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
		
        AddNewDonorController controller = loader.getController();

		Scene scene = new Scene(root);
		
		System.out.println(css.length());
		
		if (css.length() > 0)
			scene.getStylesheets().add(name_fxml_css+css);
		
		st.setScene(scene);
		st.setResizable(resize);
		
		if (title.length() > 0)
			st.setTitle(title);
		
		st.show();

        //controller.setMainApp(this);
        controller.setDataRole();
        controller.setDataBloodGroup();
        controller.setImageMsg();
        
		st.setOnCloseRequest(event -> {
		    try {
		    	DB_connect.dbConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}
	
}
