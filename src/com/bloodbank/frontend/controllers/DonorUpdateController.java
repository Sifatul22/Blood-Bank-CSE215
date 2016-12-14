package com.bloodbank.frontend.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;

import com.bloodbank.db_ops.DB_connect;
import com.bloodbank.db_ops.Donors_Data;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class DonorUpdateController extends CommonMethods implements Initializable {

	

	@FXML
	private TextField name;

	@FXML
	private TextField contact;

	@FXML
	private TextField area;

	@FXML
	private TextField last_date;

	@FXML
	private TextField email;

	@FXML
	private TextField password;

	@FXML
	private ComboBox<String> roleCombo;

	@FXML
	private ComboBox<String> list_groups;

	@FXML
	private ImageView img_preview;

	@FXML
	private Text image_size_max;
	@FXML
	private FileInputStream img_stream = null;

	private Paint prevColor;

	@FXML
	private Label donorId;

	private int uid;
	
	@FXML
	private Text updateStatus;

	@FXML private Button close;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void imgUploadHandler() {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Profile Picture");
		
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            ); 
		fileChooser.getExtensionFilters().addAll(

                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
                
				);
		File file = fileChooser.showOpenDialog(img_preview.getScene().getWindow());
		
		if (file == null)
			return;
		
		if (file.length() > (MAX_FILE_SIZE * 1024 * 1024)) {
			prevColor = image_size_max.getFill();
			image_size_max.setFill(Color.RED);
			return;
		} else {
			image_size_max.setFill(prevColor);
		}
			
		
		String imagepath;
		try {
			imagepath = file.toURI().toURL().toString();
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			imagepath = "";
			return;
		}
        
		Image image = new Image(imagepath);
		img_preview.setImage(image);
		
		try {
			img_stream = new FileInputStream(file);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		
		
	}

	@FXML
	public void saveHandle() {
		
		Donors_Data donor = db.getDonorDataByID(uid);

		Map<String, String> updateData = new HashMap<>();
		
		updateData.put("donor_name", name.getText());
		updateData.put("contact_no", contact.getText());
		updateData.put("area", area.getText());
		updateData.put("donation_dates", last_date.getText());
		updateData.put("password", DigestUtils.md5Hex(password.getText()));
		updateData.put("email", email.getText());
		updateData.put("role", ( (roleCombo.getValue() == "admin") ? "1" : "2") );
		//updateData.put("role", roleCombo.getValue() );
		updateData.put("blood_group", list_groups.getValue());

		
		int stat = db.updateDonor(donor.getID(), updateData);
		//updateStatus.setText("Success");

		if (stat == 1) {
			updateStatus.setFill(Color.GREEN);
			updateStatus.setText("Success");
		}
		else {
			updateStatus.setFill(Color.RED);
			updateStatus.setText("Error!");

		}
		
		
		try {
			String imgStat = db.updateImage(donor.getID(), img_stream);

			System.out.println(imgStat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());

		}
			
		
	}
	
	@FXML
	public void LoadDataToView(int uid) {

		Donors_Data donor = db.getDonorDataByID(uid);

		this.uid = uid; 
		name.setText(donor.getName());
		contact.setText(donor.getContactNo());
		area.setText(donor.getArea());
		last_date.setText(donor.getDonationDates());
		email.setText(donor.getEmail());
		password.setText(donor.getPassword());
		donorId.setText(Integer.toString(donor.getID()));
		ArrayList<String> roleCo = new ArrayList<>();
		roleCo.add("admin");
		roleCo.add("user");
		roleCombo.getItems().addAll(roleCo);
		
		String the_role_str = ( (donor.getRole() == 2) ? "user" : "admin" );
		//System.out.println(the_role_str);
		//System.out.println(roleCo.indexOf(the_role_str));
		roleCombo.getSelectionModel().select(roleCo.indexOf(the_role_str));
		list_groups.getItems().addAll("A+", "O+", "B+", "AB+", "A-", "O-", "B-", "AB-");

		ArrayList<String> list_groupsCo = new ArrayList<>();
		list_groupsCo.add("A+");
		list_groupsCo.add("O+");
		list_groupsCo.add("B+");
		list_groupsCo.add("AB+");
		list_groupsCo.add("A-");
		list_groupsCo.add("O-");
		list_groupsCo.add("B-");
		list_groupsCo.add("AB-");
		list_groups.getItems().addAll(list_groupsCo);
		String the_bg_str = donor.getBloodGroup();
		list_groups.getSelectionModel().select(list_groupsCo.indexOf(the_bg_str));

	try	{
			
		    InputStream is = donor.getImage();
		    BufferedImage imageBufferedImage = ImageIO.read(is);
		    Image imageImage = SwingFXUtils.toFXImage(imageBufferedImage, null);
			
		    img_preview.setImage(imageImage);
			
		} catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
	
		if (donor.getRole() == 1)
			return;
		
		System.out.println("Role dsf");

	}
	
	public void setImageMsg() {
		// TODO Auto-generated method stub
		image_size_max.setText("Max image size: " + MAX_FILE_SIZE + " MB");
	}

	@FXML
	public void closeHandle() {
		
		quit_stage_common(close);

	}
	
}
