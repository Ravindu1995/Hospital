package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "ravindu", "ravindu");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String code, String name, String address, String telephone)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement    
			String query = " insert into hospital(`hID`,`hCode`,`hName`,`address`,`telephone`)" + " values (?, ?, ?, ?, ?)"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, code);    
			preparedStmt.setString(3, name);    
			preparedStmt.setString(4, address);    
			preparedStmt.setString(5, telephone);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newHospitals = readHospitals(); 
			output =  "{\"status\":\"success\", \"data\": \"" +        
					newHospitals + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the hospital.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	public String readHospitals()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Hospital Code</th><th>Hospital Name</th><th>Hospital address</th><th>Hospital telephone</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from hospital";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String hID = Integer.toString(rs.getInt("hID"));     
				String hCode = rs.getString("hCode");     
				String hName = rs.getString("hName");     
				String address = rs.getString("address");     
				String telephone = rs.getString("telephone"); 
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidHospitalIDUpdate\' name=\'hidHospitalIDUpdate\' type=\'hidden\' value=\'" + hID + "'>" 
							+ hCode + "</td>";      
				output += "<td>" + hName + "</td>";     
				output += "<td>" + address + "</td>";     
				output += "<td>" + telephone + "</td>"; 
	 
				// buttons     
//				output += "<td><input name=\'btnUpdate\' type=\'button\' value=\'Update\' class=\' btnUpdate btn btn-secondary\'></td>"
//						//+ "<td><form method=\"post\" action=\"items.jsp\">      "
//						+ "<input name=\'btnRemove\' type=\'submit\' value=\'Remove\' class=\'btn btn-danger\'> "
//						+ "<input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" + itemID + "\">" + "</form></td></tr>"; 
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hid='" + hID + "'>" + "</td></tr>"; 
			} 
	 
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the hospitals.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	
	public String updateHospital(String ID, String code, String name, String address, String telephone)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE hospital SET hCode=?,hName=?,address=?,telephone=? WHERE hID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, code);    
			preparedStmt.setString(2, name);    
			preparedStmt.setString(3, address);    
			preparedStmt.setString(4, telephone);    
			preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospitals = readHospitals();    
			output = "{\"status\":\"success\", \"data\": \"" +        
					newHospitals + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the hospital.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteHospital(String hID)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement    
			String query = "delete from hospital where hID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(hID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospitals = readHospitals();    
			output = "{\"status\":\"success\", \"data\": \"" +       
					newHospitals + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the hospital.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	 
}




