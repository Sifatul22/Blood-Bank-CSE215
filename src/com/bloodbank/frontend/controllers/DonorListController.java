package com.bloodbank.frontend.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.bloodbank.db_ops.DB_connect;
import com.bloodbank.db_ops.Donors_Data;
import com.bloodbank.frontend.Main;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class DonorListController extends LoaderTheTable implements Initializable {

    @FXML private TableView<Table_Data_Model> tableView;
    @FXML private TableColumn<Table_Data_Model, String> name;
    @FXML private TableColumn<Table_Data_Model, String> blood_group;
    @FXML private TableColumn<Table_Data_Model, String> area;
    @FXML private TableColumn<Table_Data_Model, String> id;
    @FXML private TableColumn<Table_Data_Model, String> contact;
    @FXML private Button close;
    
    private LoaderTheTable mainApp;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		blood_group.setCellValueFactory(cellData -> cellData.getValue().bloodGroupProperty());
		area.setCellValueFactory(cellData -> cellData.getValue().areaProperty());
		id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		contact.setCellValueFactory(cellData -> cellData.getValue().contactProperty());

		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() < 2)
					return;
				   Table_Data_Model pos1 = tableView.getSelectionModel().getSelectedItem();

				   int selectedId = Integer.parseInt(pos1.getId());
				   
				   //System.out.println(logged_in_user_id);
				   
				   //Donors_Data thisDonorsData = db.getDonorDataByID(logged_in_user_id);

//				   if (db.getDonorDataByID(logged_in_user_id).getRole() == 1 || (selectedId == logged_in_user_id))
//					   addNewStageViewProfile(pos1.getName(), "DonorUpdate.fxml", "", false, selectedId);
//				   else
//					   addNewStageViewProfile(pos1.getName(), "DonarProfile.fxml", "", false, selectedId);

				   if (db.getDonorDataByID(logged_in_user_id).getRole() == 1 || (selectedId == logged_in_user_id))
					   addNewStageUpdateProfile(pos1.getName(), "DonorUpdate.fxml", "", false, selectedId);
				   else
					   addNewStageViewProfile(pos1.getName(), "DonarProfile.fxml", "", false, selectedId);

				   
			   //addNewStageViewProfile(pos1.getName(), "DonorUpdate.fxml", "", false, selectedId);

			}
			
			
		});
	}
	
    public void setMainApp(LoaderTheTable loaderTheTable) {
        this.mainApp = loaderTheTable;
        
        
    	tableView.getColumns().get(0).setVisible(false);
    	tableView.getColumns().get(0).setVisible(true);
        // Add observable list data to the table
        tableView.setItems(loaderTheTable.getDonorsData());

    }
    
    @FXML
    public void closeHandle(ActionEvent evt) {
    	
		quit_stage_common(close);
        try {
			//DB_connect.dbConn.close();
			//DB_connect.connect.close();
			//DB_connect.db_stat.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

    	
    }

    

}
