package com.bloodbank.frontend.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.bloodbank.frontend.FxmlPackageNames;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class SampleLoginController extends CommonMethods implements Initializable, FxmlPackageNames {

	@FXML private Button close;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void closeHandle() {
		
		quit_stage_common(close);

	}
	
}
