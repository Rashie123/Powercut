package model; 
import java.sql.*; 


public class Power 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = 
 DriverManager.getConnection( 
		 "jdbc:mysql://127.0.0.1:3306/ceb", "root", ""); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 





public String readPower() 
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
 output = "<table border='1'><tr><th>Location Name</th>" 
		 +"<th>Related Zone</th><th>Time Period</th>"
		 +"<th>Schedule Date</th>" 
 + "<th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from power"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String scheduleID = Integer.toString(rs.getInt("scheduleID")); 
 String locationName = rs.getString("locationName"); 
 String relatedZone = rs.getString("relatedZone"); 
 String timePeriod = rs.getString("timePeriod"); 
 String scheduleDate = rs.getString("scheduleDate"); 
 
 // Add into the html table
 output += "<tr><td>" + locationName + "</td>"; 
 output += "<td>" + relatedZone + "</td>"; 
 output += "<td>" + timePeriod + "</td>"; 
 output += "<td>" + scheduleDate + "</td>"; 
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-scheduleid='" + scheduleID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-scheduleid='" + scheduleID + "'></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
catch (Exception e) 
 { 
 output = "Error while reading the schedule."; 
 System.err.println(e.getMessage()); 
 } 
return output; 
}






public String insertPower(String name, String zone, 
		 String tperiod, String sdate) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for inserting."; 
		 } 
		 // create a prepared statement
		 String query = " insert into power (`scheduleID`,`locationName`,`relatedZone`,`timePeriod`,`scheduleDate`)"
		 + " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setString(3, zone); 
		 preparedStmt.setString(4, tperiod); 
		 preparedStmt.setString(5, sdate); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newPower = readPower(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newPower + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the schedule.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		





public String updatePower(String ID, String name, String zone, 
		 String tperiod, String sdate) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE power SET locationName=?,relatedZone=?,timePeriod=?,scheduleDate=? WHERE scheduleID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, zone); 
		 preparedStmt.setString(3, tperiod); 
		 preparedStmt.setString(4, sdate); 
		 preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newPower = readPower(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newPower + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the schedule.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		
		
		public String deletePower(String scheduleID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from power where scheduleID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(scheduleID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newPower = readPower(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newPower + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the schedule.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}


