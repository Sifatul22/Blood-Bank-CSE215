package com.bloodbank.db_ops;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

import com.bloodbank.frontend.FxmlPackageNames;

public class DB_connect implements FxmlPackageNames {

	public static Statement db_stat;
	public static Connection connect;
	public static Connection dbConn;
	public static int count_connect;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/*
	* Connect to the database, initialize the class
	*/
	
	public DB_connect(String dbUrl, String user, String pass) {
		
		//String dbUrl = "jdbc:mysql://127.0.0.1/blood_bank";
		//String user = "test";
		//String pass = "test";

		//String dbUrl = "jdbc:mysql://ashik72.me/java_blood_bank";
		//String user = "jbb01";
		//String pass = "Qx4dVRduhbcUWK9Z";

		
//		try {
//				connect = DriverManager.getConnection(dbUrl, user, pass);
//				this.dbConn = connect;
//				DB_connect.count_connect++;
//				
//			
//			db_stat = connect.createStatement();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			
//
//		}
		
		//System.out.println(DB_connect.count_connect + " mysql connections");
	}
	
	public DB_connect() {
		
		//this("jdbc:mysql://ashik72.me/java_blood_bank", "jbb01", "Qx4dVRduhbcUWK9Z");
	}
	
	
	
	public ArrayList<Donors_Data> getDonorData() {
		
		return getDonorData("donors_list");
	}
	
	
	public ArrayList<Donors_Data> getDonorData(String table_name) {
		
		ArrayList<Donors_Data> donors_data = new ArrayList<Donors_Data>();

		ResultSet rset;

		try {
			
			//DB_connect db = new DB_connect("jdbc:mysql://"+db_host+"/"+db_name+"?autoReconnect=true", db_user, db_pass);

			Connection connect = DriverManager.getConnection("jdbc:mysql://"+db_host+"/"+db_name+"?autoReconnect=true", db_user, db_pass);
			
			rset = connect.createStatement().executeQuery("select * from "+table_name+";");
			
			while (rset.next()) {			
				
				Donors_Data d_data = new Donors_Data(rset.getInt("id"), rset.getString("donor_name"), rset.getString("blood_group"), rset.getString("contact_no"), rset.getString("area"), rset.getString("donation_dates"), rset.getString("password"), rset.getInt("role"), rset.getBinaryStream("image"), rset.getString("email"));

				donors_data.add(d_data);

			}
			
			rset.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return donors_data;

		}
		

		return donors_data;
		
	}
	
	public Donors_Data getDonorDataByID(int id) {
		
		Donors_Data result = new Donors_Data();
		
		

		try {
			
			Connection connect = DriverManager.getConnection("jdbc:mysql://"+db_host+"/"+db_name+"?autoReconnect=true", db_user, db_pass);

			PreparedStatement statement = connect.prepareStatement("select * from donors_list where id= ? ;");

			statement.setInt(1, id);
					
			ResultSet rset = statement.executeQuery();
			
			while (rset.next()) {
				
				Donors_Data d_data = new Donors_Data(rset.getInt("id"), rset.getString("donor_name"), rset.getString("blood_group"), rset.getString("contact_no"), rset.getString("area"), rset.getString("donation_dates"), rset.getString("password"), rset.getInt("role"), rset.getBinaryStream("image"), rset.getString("email"));

				result = d_data;
				
			}
			
			connect.close();
			statement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;

		}

		return result;

	}
	
	public String updateImage(int id, FileInputStream image) {
		
		int rset = 0;
		
		try {
			
			Connection connect = DriverManager.getConnection("jdbc:mysql://"+db_host+"/"+db_name+"?autoReconnect=true", db_user, db_pass);

			PreparedStatement statement = connect.prepareStatement("Update donors_list SET image= ? WHERE id= ?");
			statement.setBlob(1, image);
			statement.setInt(2, id);

			rset = statement.executeUpdate();
			connect.close();
			statement.close();

			return ((Integer) rset).toString();
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}

	}
	
	public String addDonor(String name, String blood_group, String contact_no, String area, String donation_dates, String password, String role, FileInputStream image, String email) {
		
		//System.out.println(data);

		int rset = 0;
		
		try {
			
			Connection connect = DriverManager.getConnection("jdbc:mysql://"+db_host+"/"+db_name+"?autoReconnect=true", db_user, db_pass);

			PreparedStatement statement = connect.prepareStatement("INSERT INTO donors_list (id, donor_name, contact_no, area, donation_dates, password, role, blood_group, image, email) VALUES ( NULL , ? , ? , ? , ? , ? , ? , ? , ? , ? );");
			statement.setString(1, ( (name.length() > 0) ? name : "") );
			statement.setString(2, ( (contact_no.length() > 0) ? contact_no : "") );
			statement.setString(3, ( (area.length() > 0) ? area : "") );
			statement.setString(4, ( (donation_dates.length() > 0) ? donation_dates : "") );
			statement.setString(5, ( (password.length() > 0) ? DigestUtils.md5Hex(password) : "") );
			statement.setInt(6, ( (role.length() > 0) ? Integer.parseInt(role) : 2) );
			statement.setString(7, ( (blood_group.length() > 0) ? blood_group : "") );
			statement.setString(9, ( (email.length() > 0) ? email : "") );

			statement.setBlob(8, ( (image != null) ? image : null) );
			
			if (name.isEmpty() || contact_no.isEmpty() || email.isEmpty() || blood_group.isEmpty() || password.isEmpty() )
				return "Please fill up required fields!";
			
			//PreparedStatement statement2 = connect.prepareStatement();
			//db_stat.executeUpdate("INSERT INTO donors_list (id, donor_name, contact_no, area, donation_dates, password, role, blood_group, image, email) VALUES ( '' , 'dsfdfg namef', '5223121', 'Savar, Mirpur', '5/7/15, 3/6/9', 'sdfgfdfg', 2, 'B+', 'fdfdg.png', 'dfgf@fddghf.net')");
			rset = statement.executeUpdate();
			
			connect.close();
			statement.close();

			
			return ((Integer) rset).toString();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return e.getMessage();
		}

		
		//return rset;
	}
	
	public int updateDonor(int id, Map<String, String> updateData) {
		
		int rset = 0;
		
		if (updateData.isEmpty())
			return rset;

		try {
			
			int count = updateData.size();
			String updateString = "";
			String keyName = "";
			String valData = "";

			Set<String> keySet = updateData.keySet();
			int i = 0;
			while (count != 0) {
				
				keyName = (String) keySet.toArray()[i];
				valData = updateData.get(keySet.toArray()[i]);
				updateString += keyName + "= ? ";
				
				if (count != 1)
					updateString += ", ";
				
				i++;
				count--;
			}
			
			Connection connect = DriverManager.getConnection("jdbc:mysql://"+db_host+"/"+db_name+"?autoReconnect=true", db_user, db_pass);

			String sql = "Update donors_list SET "+updateString+"WHERE id = ? ;";
			PreparedStatement statement = connect.prepareStatement(sql);
			count = updateData.size();
			
			i = 1;
			int j = 0;
			while (count != 0) {
			
				keyName = (String) keySet.toArray()[j];
				valData = updateData.get(keySet.toArray()[j]);

				statement.setString(i, valData);
				
				i++;
				j++;
				count--;	
			}
			statement.setInt(updateData.size()+1, id);
			
			rset = statement.executeUpdate();
			connect.close();
			statement.close();

			return rset;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			return rset;
		}

		
		
	}
	
	public int deleteDonor(int id) {
		
		int rset = 0;
		
		try {
			String sql = "DELETE FROM donors_list WHERE id = "+id+";";
			Connection connect = DriverManager.getConnection("jdbc:mysql://"+db_host+"/"+db_name+"?autoReconnect=true", db_user, db_pass);

			PreparedStatement statement = connect.prepareStatement(sql);
			
			rset = statement.executeUpdate();
			
			connect.close();
			statement.close();

			return rset;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return rset;

		}

	}
	
	public ArrayList<Donors_Data> searchDonor(String search_string) {
		
		ArrayList<Donors_Data> donors_data = getDonorData("donors_list");

		
		if (donors_data.isEmpty())
			return donors_data;
		

		ArrayList<Integer> id_collection = new ArrayList<>();
		
		for (Donors_Data donor_data : donors_data) {
			
			//System.out.println(donor_data.toString());
			
		  String pattern = search_string;
		  
	      Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
	      
	      Matcher m = r.matcher(donor_data.toString());
	      
	      if (m.find( )) { 
	    	 
	    	  id_collection.add( donor_data.getID() );

	      }

		}
		
		if (id_collection.isEmpty())
			return donors_data;

		donors_data.clear();
		
		for (int id : id_collection) {
			
			donors_data.add(this.getDonorDataByID(id));
		}
		
		
		return donors_data;
		
	} 
	
	public int user_login(String email, String password) {
		
		int res = 0;
		if (email.isEmpty() || password.isEmpty())
			return res;
		
		ArrayList<Donors_Data> search_result = this.searchDonor(email);

		password = DigestUtils.md5Hex(password);

		for (Donors_Data user: search_result) {
			
			if (user.getPassword().equals(password))
				return (int) user.getID();
			
		}
		
		
		return res;
	}

}
