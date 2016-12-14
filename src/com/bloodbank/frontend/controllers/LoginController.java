package com.bloodbank.frontend.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.bloodbank.db_ops.DB_connect;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class LoginController extends CommonMethods implements Initializable {

	@FXML
	private TextArea email;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Button loginbtn;

	@FXML
	private Label stat_label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				loginbtn.requestFocus();
			}
			

		});
		
	}
	
	@FXML
	public void login_user(ActionEvent evt) {
		
		Integer loginStat = new Integer(0);
		
		loginStat = db.user_login(email.getText(), password.getText());
		
		
		
		
		if (loginStat == 0 || loginStat == null)
			stat_label.setText("Login Error! Please try again.");
		else {

			stat_label.setText("");
			CommonMethods.logged_in_user_id = loginStat;
			addNewStage("Dashboard", "HomePage.fxml", "", false);
		    this.quit_stage_common(loginbtn);
			//this.quit();
		}
				
	}

	@FXML
	public void register_user(ActionEvent evt) {
		
	}

	@FXML
	public void quit(ActionEvent evt) {
		
		Platform.exit();
	}

	public void sampleLoginHandler(ActionEvent evt) {

		addNewStage("Sample Login", "sampleLogin.fxml", "", false);

	}
	
}
