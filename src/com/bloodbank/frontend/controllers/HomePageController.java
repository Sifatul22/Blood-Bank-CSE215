package com.bloodbank.frontend.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.bloodbank.db_ops.DB_connect;
import com.bloodbank.db_ops.Donors_Data;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePageController extends LoaderTheTable implements Initializable{

	@FXML private Button add_update;
	
	@FXML private Button logOutbtn;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (CommonMethods.logged_in_user_id == 0) {

			this.addNewStage("Login.fxml");
			
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					Platform.exit();
				}
				
				
			});
			
		}
		
		
			
	}
	
	public void user_add_update_handle(ActionEvent evt) {
		
		Donors_Data donor = db.getDonorDataByID(logged_in_user_id);
		
		System.out.println(logged_in_user_id + " logged_in_user_id ");
		System.out.println(donor + " role ");
		
		if (donor.getRole() == 1)
			this.addNewStageDonorAdd("Add Donor", "AddNewDonor.fxml", "", false);
		else
			this.addNewStageUpdateProfile("Update Profile " + donor.getName(), "DonorUpdate.fxml", "", false, logged_in_user_id);
			
			
	}
	
	public void donorListDBHandle(ActionEvent evt) {
		
		this.addNewStageTable("Donor List", "DonorList.fxml", "", true);
		
		
		
	}
	
	public void searchHandle(ActionEvent evt) {
		
		this.addNewStageSearch("Donor Search", "SearchDonor.fxml", "", true);

	}
	
	@FXML
	public void exitHandle() {
		
		 try {
		    	//DB_connect.dbConn.close();
				Platform.exit();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
		
	}
	
	public void logOutHandle() {
		
			CommonMethods.logged_in_user_id = 0;

			this.addNewStage("Login.fxml");

			quit_stage_common(logOutbtn);
		    

	}

	
	
}
