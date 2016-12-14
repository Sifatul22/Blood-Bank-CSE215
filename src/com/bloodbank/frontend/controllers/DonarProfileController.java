package com.bloodbank.frontend.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.bloodbank.db_ops.DB_connect;
import com.bloodbank.db_ops.Donors_Data;
import com.sun.javafx.tk.Toolkit;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;

public class DonarProfileController extends CommonMethods implements Initializable {
	
	@FXML
	private Label name;

	@FXML
	private Label contact;

	@FXML
	private Label area;
	
	@FXML
	private Label donation_dates;

	@FXML
	private Label email;
	
	@FXML
	private Label role;

	
	@FXML
	private Text blood_group;

	@FXML
	private ImageView image;

	@FXML
	private Button close;

	private int uid;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void closeHandle() {
		
		quit_stage_common(close);
        try {
			//DB_connect.dbConn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}
	
	@FXML
	public void LoadDataToView(int uid) {
		
		this.uid = uid;
		
		Donors_Data the_donor = db.getDonorDataByID(uid);
		
		name.setText(the_donor.getName());
		contact.setText(the_donor.getContactNo());
		donation_dates.setText(the_donor.getDonationDates());
		role.setText( (the_donor.getRole() == 1) ? "Admin" : "User" );
		email.setText(the_donor.getEmail());
		area.setText(the_donor.getArea());
		blood_group.setText(the_donor.getBloodGroup());
		//image.setImage(the_donor.getImage());
				
		try	{
			
		    InputStream is = the_donor.getImage();
		    BufferedImage imageBufferedImage = ImageIO.read(is);
		    Image imageImage = SwingFXUtils.toFXImage(imageBufferedImage, null);
			
            image.setImage(imageImage);
			
		} catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	
	

}
