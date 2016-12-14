package com.bloodbank.examples;

import java.util.*;

import com.bloodbank.db_ops.DB_connect;


public class General_Example {

	public static void main(String[] args) {

		//DB_connect db01 = new DB_connect();
		
		DB_connect db01 = new DB_connect();

		///git test
		//System.out.println(db01.getDonorData().get(2));
		System.out.println(db01.getDonorData());
		//System.out.println(db01.getDonorDataByID(3).getDonationDates());

		//System.out.println(db01.getDonorDataByID(3).getDonationDatesArray()[1]);
		//int stat = db01.addDonor("my d fdname ` ^ &8 @ ` ~ hjj", "A+", "0167522", "Savar, Dhaka", "8/1/95, 4/05/2016", "adfdsffdgd", "1", "dsgg.png", "dgfdg@fdsfgdf.com");
		
		Map<String, String> updateData = new HashMap<>();
		
		updateData.put("email", "my2@fdggdf.net");
		updateData.put("donor_name", "hash name2");
		
		//int stat = db01.updateDonor(11, updateData);
		//int stat = db01.deleteDonor(10);
		
		System.out.println(db01.user_login("asdsdf@dsfgdfgh.net", "password"));
	}
	
	

}
