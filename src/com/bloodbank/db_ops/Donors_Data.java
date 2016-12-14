package com.bloodbank.db_ops;

import java.io.InputStream;

import com.mysql.jdbc.Blob;

public class Donors_Data {

	private int id;
	
	private String name;
	
	private String blood_group;
	
	private String contact_no;

	private String area;

	private String donation_dates;

	private String password;

	private int role;

	private InputStream image;

	private String email;

	
	public void setID(int id) {
		
		this.id = id;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}

	public void setBloodGroup(String blood_group) {
	
		this.blood_group = blood_group;
	}
	
	public void setRole(int role) {
		
		this.role = role;
	}
	
	public void setContactNo(String contact_no) {
		
		this.contact_no = contact_no;
	}

	public void setArea(String area) {
	
		this.area = area;
	}
	
	public void setDonationDates(String donation_dates) {
		
		this.donation_dates = donation_dates;
	}

	public void setPassword(String password) {
	
		this.password = password;
	}
	
	public void setImage(InputStream image) {
		
		this.image = image;
	}

	public void setEmail(String email) {
	
		this.email = email;
	}
	
	public int getID() {
		
		return this.id;
	}
	
	public String getName() {
		
		return this.name;
	}

	public String getBloodGroup() {
	
		return this.blood_group;
	}
	
	public String getContactNo() {
		
		return this.contact_no;
	}

	public String getArea() {
	
		return this.area;
	}
	
	public String[] getAreaArray() {
		
		String areas = this.getArea();
		
		String areas_array[] = areas.split(",");
		
		String[] areas_array_trimmed = new String[areas_array.length];
		
		for (int i = 0; i < areas_array.length; i++)
			areas_array_trimmed[i] = areas_array[i].trim();

		
		return areas_array_trimmed;
		
	}
	
	public String getDonationDates() {
		
		return this.donation_dates;
	}
	
	public String[] getDonationDatesArray() {
		
		String dates = this.getDonationDates();
		
		String dates_array[] = dates.split(",");

		String[] dates_array_trimmed = new String[dates_array.length];
		
		for (int i = 0; i < dates_array.length; i++)
			dates_array_trimmed[i] = dates_array[i].trim();

		
		return dates_array_trimmed;
		
	}
	
	public String getPassword() {
		
		return this.password;
	}

	public int getRole() {
	
		return this.role;
	}
	
	public InputStream getImage() {
		
		return this.image;
	}
	

	
	public String getEmail() {
		
		return this.email;
	}
	
	
	@Override
	public String toString() {
		return "Donors_Data [id=" + id + ", name=" + name + ", blood_group=" + blood_group + ", contact_no="
				+ contact_no + ", area=" + area + ", donation_dates=" + donation_dates + ", password=" + " "
				+ ", role=" + role + ", " + ", email=" + email + "]";
	}

	public Donors_Data(int id, String name, String blood_group, String contact_no, String area, String donation_dates, String password, int role, InputStream image, String email) {
		
		this.setID(id);
		this.setName(name);
		this.setBloodGroup(blood_group);
		this.setContactNo(contact_no);
		this.setArea(area);
		this.setDonationDates(donation_dates);
		this.setPassword(password);
		this.setRole(role);
		this.setImage(image);
		this.setEmail(email);
	}
	
	public Donors_Data() {
		
		
	}
	
}
