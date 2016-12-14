package com.bloodbank.frontend.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.bloodbank.frontend.FxmlPackageNames;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class AddNewDonorController extends CommonMethods implements Initializable, FxmlPackageNames {

	@FXML
	private ImageView img_preview;
	@FXML
	private Button img_upload_btn;
	@FXML
	private FileInputStream img_stream = null;
    
	@FXML
	private ComboBox<String> roleCombo;

	@FXML
	private ComboBox<String> list_groups;

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
	private PasswordField password;

	@FXML
	private Text errorText;

	@FXML
	private Text image_size_max;
	
	@FXML private Button close;

	private Paint prevColor;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		//roleCombo.getItems().addAll(2, 1);

	
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
		
	
		
		Integer roleVal = new Integer(( (roleCombo.getValue() == "admin") ? 1 : 2 ));
		
		String stat = db.addDonor(name.getText(), list_groups.getValue(), contact.getText(), area.getText(), last_date.getText(), password.getText(), roleVal.toString(), img_stream, email.getText());
		
		errorText.setText(stat);

		if (stat.equals("1"))
			errorText.setText("Success");

		System.out.println(stat);
	}
	
	
	public void setDataRole(){
//
		
		roleCombo.getItems().clear();

		roleCombo.getItems().addAll("admin", "user");

		
		}
	
	public void setDataBloodGroup(){
//
		list_groups.getItems().clear();

		list_groups.getItems().addAll("A+", "O+", "B+", "AB+", "A-", "O-", "B-", "AB-");

		
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
